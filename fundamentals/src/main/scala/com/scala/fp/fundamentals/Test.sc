package chapter2

import com.scala.fp.fundamentals.Fibonnacci._
import com.scala.fp.fundamentals.Compose._
import com.scala.fp.fundamentals.Factorial._
import com.scala.fp.fundamentals.Sorted._
import com.scala.fp.fundamentals.Curry._

object Test {

	factorial(10)                             //> res0: Int = 3628800
  
  // 0 1 1 2 3 5 8 13 21 34
 	fib(5)                                    //> res1: Int = 3
  fib(1)                                          //> res2: Int = 0
  fib(6)                                          //> res3: Int = 5
	fib(7)                                    //> res4: Int = 8
	fib(10)                                   //> res5: Int = 34
	
	isSorted(Array(1,2,3), (a: Int, b: Int) => a <= b)
                                                  //> res6: Boolean = true
  
  isSorted(Array(1,2,3,7), (a: Int, b: Int) => a <= b)
                                                  //> res7: Boolean = true
  isSorted(Array(1,2,3,0), (a: Int, b: Int) => a <= b)
                                                  //> res8: Boolean = false
                                                  
  val fc = curry((a: Int, b : Int) => a + b)      //> fc  : Int => (Int => Int) = <function1>

	fc(3)(2)                                  //> res9: Int = 5
	
	val f = (x: Int) => x * x                 //> f  : Int => Int = <function1>

	val g = (y: Int) => y + 1                 //> g  : Int => Int = <function1>
	
	val fg = comp(f, g)                       //> fg  : Int => Int = <function1>

	fg(2)                                     //> res10: Int = 9
}