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
}