package parallelism

import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.{Callable, CountDownLatch, ExecutorService, TimeUnit, Future => ConcurrentFuture}

import scala.util.Try

object ParallelModule {

  sealed trait Future[A] {
    private[parallelism] def apply(k: A => Unit): Unit
  }

  type Par[A] = ExecutorService => ConcurrentFuture[A]

  type Par1[A] = ExecutorService => ParallelModule.Future[A]

  def sum(ints: IndexedSeq[Int]): Par[Int] = {
    if ( ints.size <=1 )
      Par.unit(ints.headOption getOrElse 0)
    else {
      val (l, r) = ints.splitAt(ints.length / 2)

      Par.map2(Par.fork(sum(l)), Par.fork(sum(r)))(_ + _)
    }
  }

  def findMax(ints: IndexedSeq[Int]) : Par[Int] = {

    def loop(ints: IndexedSeq[Int], cm: Int) : Par[Int] =  {

      if (ints.size == 0) {
        Par.unit(cm)
      } else {
        val (l, r) = ints.splitAt(ints.length / 2)
        Par.map2(Par.fork(loop(l, cm)), Par.fork(loop(r, cm)))((a,b) => if(a > b) a else b)
      }
    }

    loop(ints, ints.headOption.getOrElse(0))
  }

  def countNumberOfWords(paragraphs: List[String]) : Par[Int] = {

    def numberOfWordsPerLine(line: String) : Int = line.split(" ").size

    if (paragraphs.size == 0) {
      Par.unit(0)
    } else {
      val (l, r) = paragraphs.splitAt(paragraphs.length / 2)
      Par.map2(Par.fork(countNumberOfWords(l.tail)), Par.fork(countNumberOfWords(r.tail)))((_,_) => numberOfWordsPerLine(l.head) + numberOfWordsPerLine(r.head))
    }
  }

  object Par {

    def id[A](a: A) = a

    def unit[A](a: A) : Par[A] = ((es: ExecutorService) => UnitFuture(a))

    def unit1[A](a: A): Par1[A] =
        es => new Future[A] {
          override private[parallelism] def apply(k: (A) => Unit) = k(a)
        }

    def lazyUnit[A](a: => A) : Par[A] = fork(unit(a))

    def map2[A, B, C](pa: Par[A], pb: Par[B])(f: (A, B) => C) : Par[C] = (es: ExecutorService) => {
        val a = pa(es).get
        val b = pb(es).get
        UnitFuture(f(a,b))
    }

    def map2[A, B, C](pa: Par[A], pb: Par[B], timeout: Int, timeUnit: TimeUnit)(f: (A, B) => C) : Par[C] = (es: ExecutorService) => {
      val a =  Try(pa(es).get(timeout, timeUnit))
      val b = Try(pb(es).get(timeout,timeUnit))

      a.flatMap(va => b.map(vb => f(va,vb)))
        .map(vc => UnitFuture(vc))
        .get
    }

    def map3[A, B, C, D](pa: Par[A], pb: Par[B], pc: Par[C])(f: (A,B,C) => D): Par[D] = {
      val m = map2(pa, pb)((a, b) =>  f(a, b, _))
      map2(m, pc)((fc, c) => fc(c))
    }

    def map4[A, B, C, D, E](pa: Par[A], pb: Par[B], pc: Par[C], pd: Par[D])(f: (A,B,C,D) => E): Par[E] = {
      val m = map3(pa, pb, pc)((a, b, c) =>  f(a, b, c, _))
      map2(m, pd)((fd, d) => fd(d))
    }

    def map5[A, B, C, D, E, F](pa: Par[A], pb: Par[B], pc: Par[C], pd: Par[D], pe: Par[E])(f: (A,B,C,D,E) => F): Par[F] = {
      val m = map4(pa, pb, pc, pd)((a, b, c, d) =>  f(a, b, c, d, _))
      map2(m, pe)((fe, e) => fe(e))
    }

    def map[A,B](par: Par[A])(f: A => B) : Par[B] = map2(par, unit(()))((a:A, _) => f(a))

    def fork[B](a: => Par[B]) : Par[B] = es => es.submit(new Callable[B] {
      override def call(): B = a(es).get
    })

    def fork1[A](a: => Par1[A]): Par1[A] =
        es => new Future[A] {
          override def apply(k: (A) => Unit): Unit =
              eval(es)(a(es)(k))
        }


    def eval(es: ExecutorService)(r: => Unit): Unit = {
      es.submit(new Callable[Unit] {def call = r})
    }

    def delay[A](fa: => Par[A]): Par[A] = es => fa(es)

    def run[A](s: ExecutorService)(a: Par[A]): ConcurrentFuture[A] = a(s)

    def run1[A](s: ExecutorService)(p: Par1[A]): A = {
      val ref = new AtomicReference[A]
      val latch = new CountDownLatch(1)

      p(s) {a => ref.set(a); latch.countDown()}

      latch.await

      ref.get
    }

    def choice[A](cond: Par[Boolean])(t: Par[A], f: Par[A]): Par[A] =
      es =>
        if(run(es)(cond).get) t(es)
        else f(es)

    def choiceN[A](n: Par[Int])(choices: List[Par[A]]) : Par[A] =
      es => {
          val vn = run(es)(n).get()
          choices(vn)(es)
      }

    def choiceMap[K, V](key: Par[K])(choices: Map[K, Par[V]]) : Par[V] =
        es => {
          val vk = run(es)(key).get()
          choices(vk)(es)
        }

    def join[A](a: Par[Par[A]]): Par[A] =
        es => a(es).get()(es)


    def flatMap[A,B](pa: Par[A])(choices: A => Par[B]): Par[B] = {
      es => {
        val va = run(es)(pa).get()
        choices(va)(es)
      }
    }

    def flatMap2[A,B](pa: Par[A])(choices: A => Par[B]): Par[B] = join(map(pa)(choices))

    def asyncF[A,B](f: A => B): A => Par[B] = (a: A) => unit(f(a))

    def sortPar(parList: Par[List[Int]]): Par[List[Int]] = map(parList)(_.sorted)

    def parMap[A,B](ps: List[A])(f: A => B): Par[List[B]] = {
      val fbs: List[Par[B]] = ps.map(asyncF(f))
      sequence(fbs)
    }

    def sequence[A](ps: List[Par[A]]): Par[List[A]] = fork {
      ps.foldLeft(unit(List[A]()))((b,a) => map2(b, a)((_b,_a) =>  _b :+ _a))
    }

    def parFilter[A](as: List[A])(f: A => Boolean): Par[List[A]] =  {
      val pars: List[Par[List[A]]] = as.map(asyncF(a => if(f(a)) List(a) else List()))
      map(sequence(pars))(_.flatten)
    }

    def equal[A](e: ExecutorService)(p: Par[A], p2: Par[A]): Boolean = p(e).get == p2(e).get

    private case class UnitFuture[A](get: A) extends ConcurrentFuture[A] {
      def isDone = true
      def get(timeout: Long, units: TimeUnit) = get
      def isCancelled = false
      def cancel(eventIfRunning: Boolean) = false
    }

    def laws[A, B](x: A)(f: A => B) = {
      map(unit(x)) (f) == unit(f(x))
      map(unit(x))(id) == unit(id(x))
      map(unit(x))(id) == unit(x)
      val y = unit(x)
      map(y)(id) == y

    }

  }

}
