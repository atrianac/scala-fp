package com.scala.fp.fundamentals

object Fibonnacci {

  def fib(n: Int): Int = {
    
    def aux(i: Int, a: Int, b: Int): Int = {
      if (i == n) b
      else aux(i + 1, b, a + b)
    }

    if (n == 1) 0
    else if (n == 2) 1
    else aux(2, 0, 1)
  }

}