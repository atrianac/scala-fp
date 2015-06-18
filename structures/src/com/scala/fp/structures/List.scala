package com.scala.fp.structures

sealed trait List[+A]

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }
  
  def product(ds: List[Int]): Double = ds match {
    case Nil => 1.0
    case Cons(x, xs) => x * product(xs)
  }
  
  def apply[A](as: A*): List[A] =
    if(as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  
  def tail[A](ds: List[A]): List[A] = ds match {
      case Nil => Nil
      case Cons(x, xs) => xs
  }
  
  def setHead[A](head: A, ds: List[A]) = ds match {
    case Nil => new Cons(head, Nil)
    case Cons(x, xs) => new Cons(head, xs)
  }
  
  def drop[A](lst: List[A], n: Int): List[A] = {
    def loop(lst: List[A], iter: Int): List[A] = lst match {
      case Nil => Nil
      case Cons(x, xs) => if(iter == n) xs else loop(xs, iter + 1)
    }
    if(n > 0) loop(lst, 1) else lst
  }
  
  def dropWhile[A](lst: List[A], f: A => Boolean): List[A] = {
    def loop(lst: List[A]) : List[A] = lst match {
      case Nil => Nil
      case Cons(x, xs) => if(f(x)) Cons(x, loop(xs)) else loop(xs) 
    }
    loop(lst)
  }
  
  def init[A](lst: List[A]):  List[A] = lst match {
    case Nil => Nil
    case Cons(x, Nil) => Nil 
    case Cons(x, xs) => Cons(x, init(xs))
  }
  
  def foldRight[A, B](as: List[A], z: B)(f: (A,B) => B) : B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }
  
  def foldLeft[A,B](as: List[A], z: B)(f: (B,A) => B) : B = as match {
    case Nil => z
    case Cons(x, xs) => foldLeft(xs, f(z,x))(f)
  }
  
  def foldRight2[A, B](as: List[A], z: B)(f: (A,B) => B)(g: A => (Boolean, B)) : B = as match {
    case Nil => z
    case Cons(x, xs) => if(g(x)._1) g(x)._2 else f(x, foldRight2(xs, z)(f)(g))
  }
  
  def sum2(ints: List[Int]): Int = {
    foldRight(ints, 0)((x,y) => x + y)
  }
  
  def sum3(ints: List[Int]): Int = {
    foldLeft(ints, 0)((x,y) => x + y)
  }
  
  def product2(ds: List[Int]): Double = {
    foldRight(ds, 1.0)((x,y) => x * y)
  }
  
  def product3(ds: List[Int]): Double = {
    foldRight2(ds, 1.0)((x,y) => x * y)(x => (x == 0, 0))
  }
  
  def product4(ds: List[Int]): Double = {
    foldLeft(ds, 1.0)((x,y) => x * y)
  }
  
  def lenght[A](as: List[A]): Int = {
    foldRight(as, 0)((x, y) => y + 1)
  }
  
  def reverse[A](as: List[A]): List[A] = {
    foldLeft(as, Nil:List[A])((b,a) => Cons(a,b))
  }
  
  def append[A](as: List[A], elem: A): List[A] = {
    foldRight(as, Cons(elem, Nil))( (a,b) => Cons(a,b))
  }
  
  def concat[A](as: List[List[A]]): List[A] = List.foldRight(as, Nil:List[A])((a,b) => List.foldRight(a, b)((a,b) =>Cons(a,b)))
  
  def addOne(as: List[Int]): List[Int] = as match {
    case Nil => Nil
    case Cons(x, xs) => Cons(x + 1, addOne(xs))
  }
  
  def convertToString(as: List[Double]): List[String] = as match {
    case Nil => Nil
    case Cons(x, xs) => Cons(x.toString, convertToString(xs))
  }
  
  def map[A,B](as: List[A])(f: A => B): List[B] = as match {
    case Nil => Nil
    case Cons(x, xs) => Cons(f(x), map(xs)(f))
  }
}