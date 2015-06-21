package com.scala.exceptionhandling

sealed trait Option[+A] {
  
  def map[B](f: A => B): Option[B] = this match {
    case Some(a) => new Some(f(a))
    case None => None
  }

  def flatMap[B](f: A => Option[B]): Option[B] = this match {
    case Some(a) => f(a)
    case None => None
  }

  def getOrElse[B >: A](default: => B): B = this match {
    case Some(a) => a
    case None => default
  }

  def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
    case Some(a) => this
    case _  => ob
  }

  def filter(f: A => Boolean): Option[A] = this match {
    case Some(a) => if(f(a)) this else None
    case None => None
  }
  
  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A,B) => C): Option[C] = {
    a flatMap ( _a => b flatMap ( _b => Some(f(_a, _b)) ) )
  }
}

object Option {
  
  import java.lang.Math._
  
  def mean(xs: Seq[Double]): Option[Double] = if(xs.isEmpty) None else Some(xs.sum / xs.size)
  
  def variance(xs: Seq[Double]) : Option[Double] = mean(xs) flatMap ( m => mean (xs map ( x => pow(x - m, 2) ) ) )
  
  def sequence_1[A] (a: List[Option[A]]): Option[List[A]] = a match {
    case Nil => Some(Nil)
    case x :: xs => x flatMap ( v => sequence_1(xs) map ( vi => v :: vi ) )
  }
  
  def traverse[A,B](a:List[A])(f: A => Option[B]): Option[List[B]] = a match {
    case Nil => Some(Nil)
    case x :: xs => f(x) flatMap ( v => traverse(xs)(f) map (vi => v :: vi) )
  }
  
  def sequence[A](a: List[Option[A]]): Option[List[A]] = {
    def iter(a: List[Option[A]], r: List[A]): Option[List[A]] = a match {
      case Nil => Some(r)
      case x :: xs => x match {
        case Some(a) => iter (xs , r.:+(a))
        case _ => None
      }
    }
    iter (a, List())
  } 
}

case class Some[+A](get: A) extends Option[A]

case object None extends Option[Nothing]

