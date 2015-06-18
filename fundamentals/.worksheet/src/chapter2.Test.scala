package chapter2

import chapter2.Fibonnacci._
import chapter2.Sorted._
import chapter2.Curry._
import chapter2.Compose._

object Test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(180); 
  println("Welcome to the Scala worksheet");$skip(40); val res$0 = 
  
  // 0 1 1 2 3 5 8 13 21 34
 	fib(5);System.out.println("""res0: Int = """ + $show(res$0));$skip(9); val res$1 = 
  fib(1);System.out.println("""res1: Int = """ + $show(res$1));$skip(9); val res$2 = 
  fib(6);System.out.println("""res2: Int = """ + $show(res$2));$skip(8); val res$3 = 
	fib(7);System.out.println("""res3: Int = """ + $show(res$3));$skip(9); val res$4 = 
	fib(10);System.out.println("""res4: Int = """ + $show(res$4));$skip(54); val res$5 = 
	
	isSorted(Array(1,2,3), (a: Int, b: Int) => a <= b);System.out.println("""res5: Boolean = """ + $show(res$5));$skip(58); val res$6 = 
  
  isSorted(Array(1,2,3,7), (a: Int, b: Int) => a <= b);System.out.println("""res6: Boolean = """ + $show(res$6));$skip(55); val res$7 = 
  isSorted(Array(1,2,3,0), (a: Int, b: Int) => a <= b);System.out.println("""res7: Boolean = """ + $show(res$7));$skip(96); 
                                                  
  val fc = curry((a: Int, b : Int) => a + b);System.out.println("""fc  : Int => (Int => Int) = """ + $show(fc ));$skip(11); val res$8 = 

	fc(3)(2);System.out.println("""res8: Int = """ + $show(res$8));$skip(29); 
	
	val f = (x: Int) => x * x;System.out.println("""f  : Int => Int = """ + $show(f ));$skip(28); 

	val g = (y: Int) => y + 1;System.out.println("""g  : Int => Int = """ + $show(g ));$skip(23); 
	
	val fg = comp(f, g);System.out.println("""fg  : Int => Int = """ + $show(fg ));$skip(8); val res$9 = 

	fg(2);System.out.println("""res9: Int = """ + $show(res$9))}
}
