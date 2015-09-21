package com.scala.strictlazyness

sealed trait Stream[+A] {
  
  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h())
  }
  
  def headOption_1: Option[A] = foldRight(None:Option[A])((a,b) => Option(a)) 
  
  def toList: List[A] = this match {
    case Empty => Nil
    case Cons(h, t) => h() :: t().toList
  }
  
  def take(n: Int) : List[A] = takeIter(n, 0)
  
  def take_unfold(n: Int): List[A] = Stream.unfold((this, 1)) { s =>
     s._1 match {
       case Cons(h, t) if s._2 <= n => Some((h(), (t(), s._2 + 1)))
       case _ => None
     }
  }.toList
  
  private def takeIter(n: Int, i: Int) : List[A] = this match {
    case Empty => Nil
    case Cons(h, t) => if(n == i) Nil else h() :: t().takeIter(n, i + 1)
  }
  
  def drop(n: Int) = dropIter(n, 0)
  
  private def dropIter(n: Int, i: Int) : List[A] = this match {
    case Empty => Nil
    case Cons(h, t) => if(i >= n) (h() :: t().dropIter(n, i + 1)) else t().dropIter(n, i + 1) 
  }
  
  def takeWhile(p: A => Boolean): Stream[A] = this match {
    case Empty  => Empty
    case Cons(h, t) => {
      lazy val hl = h()
      if(p(hl)) Stream.cons(hl, t().takeWhile ( p )) else Empty
    }
  }
  
  def takeWhile_unfold(p: A => Boolean) : Stream[A] = Stream.unfold(this) {
    case Cons(h, t) if(p(h())) => Some((h(), t()))
    case _ => None
  }
  
  def exist(p: A => Boolean): Boolean = this match {
    case Cons(h, t) => p(h()) || t().exist ( p )
    case _ => false
  }
  
  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Cons(h, t) => f(h(), t().foldRight(z)(f))
    case _ => z
  }
  
  def exist_1(p: A => Boolean): Boolean = foldRight(false)((a, b) => p(a) || b)
  
  def forAll(p: A => Boolean): Boolean = foldRight(true)((a, b) => p(a) && b)
  
  def takeWhile_1(p: A => Boolean): Stream[A] = foldRight(Stream.empty:Stream[A])((a, b) => if(p(a)) Stream.cons(a, b) else Empty)
  
  def filter(p: A => Boolean): Stream[A] = foldRight(Stream.empty:Stream[A])((a, b) => if(p(a)) Stream.cons(a, b) else b)
  
  def map[B](f: A => B): Stream[B] = foldRight(Stream.empty:Stream[B])((a,b) => Stream.cons(f(a), b))
  
  def map_unfold[B](f: A => B): Stream[B] = Stream.unfold(this) {
    case Cons(h, t) => Some((f(h()), t()))
    case _ => None
  }
  
  def append[S >: A](s: Stream[S]): Stream[S] = foldRight(s)((a,b) => Stream.cons(a, b))
  
  def flatMap[B](f: A => Stream[B]): Stream[B] = foldRight(Stream.empty:Stream[B])((a,b) => f(a).append(b))
  
}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  
  def cons[A](hd: => A, t1: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = t1
    
    Cons(() => head, () => tail)
  }
  
  def empty[A]: Stream[A] = Empty
  
  def apply[A](as: A*): Stream[A] = if(as.isEmpty) empty else cons(as.head, apply(as.tail:_*))
  
  def constant[A](a: A): Stream[A] = Stream.cons(a, constant(a))
  
  def constant_1[A](a: A): Stream[A] = unfold(a)(s => Option((a,s)))
  
  def from(n: Int): Stream[Int] = Stream.cons(n, from(n + 1))
  
  def from_1(n: Int): Stream[Int] = unfold(n)(ni => Some(ni, ni + 1))
  
  def fibs: Stream[Int] = {
    def aux(a: Int, b: Int): Stream[Int] = Stream.cons(a, aux(b, a + b))
    aux(1,1)
  }
  
  def fibs_1: Stream[Int] = unfold((1,1))(i => Some((i._1, (i._2, i._1 + i._2)))) 
  
  def unfold[A,S](z: S)(f: S => Option[(A,S)]): Stream[A] = {
    f(z) match {
      case Some((a,s)) => cons(a, unfold(s)(f))
      case _ => empty
    }
  }
}