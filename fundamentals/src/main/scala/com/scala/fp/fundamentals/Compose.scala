package com.scala.fp.fundamentals

object Compose {

  def comp[A, B, C](f: B => C, g: A => B): A => C = {
    (a: A) => f(g(a))
  }

}