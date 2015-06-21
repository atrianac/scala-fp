package com.scala.exceptionhandling

trait Either[+E, +A] {
  
  def map[B](f: A => B): Either[E, B] = this match {
    case Right(a) => Right(f(a))
    case Left(e) => Left(e)
  }
  
  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[E, B] = ???
}

case class Left[+E](value: E) extends Either[E, Nothing]

case class Right[+A](value: A) extends Either[Nothing, A]