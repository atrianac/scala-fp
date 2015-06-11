package chapter2

import chapter2.Fibonnacci._
import chapter2.Sorted._
import chapter2.Curry._
import chapter2.Compose._

object Test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 0 1 1 2 3 5 8 13 21 34
 	fib(5)                                    //> res0: Int = 3
  fib(1)                                          //> res1: Int = 0
  fib(6)                                          //> res2: Int = 5
	fib(7)                                    //> res3: Int = 8
	fib(10)                                   //> res4: Int = 34
	
	isSorted(Array(1,2,3), (a: Int, b: Int) => a <= b)
                                                  //> res5: Boolean = true
  
  isSorted(Array(1,2,3,7), (a: Int, b: Int) => a <= b)
                                                  //> res6: Boolean = true
  isSorted(Array(1,2,3,0), (a: Int, b: Int) => a <= b)
                                                  //> res7: Boolean = false
                                                  
  val fc = curry((a: Int, b : Int) => a + b)      //> fc  : Int => (Int => Int) = <function1>

	fc(3)(2)                                  //> res8: Int = 5
	
	val f = (x: Int) => x * x                 //> f  : Int => Int = <function1>

	val g = (y: Int) => y + 1                 //> g  : Int => Int = <function1>
	
	val fg = comp(f, g)                       //> fg  : Int => Int = <function1>

	fg(2)                                     //> res9: Int = 9
}