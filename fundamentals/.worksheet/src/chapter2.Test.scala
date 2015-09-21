package chapter2

import com.scala.fp.fundamentals.Fibonnacci._
import com.scala.fp.fundamentals.Compose._
import com.scala.fp.fundamentals.Factorial._
import com.scala.fp.fundamentals.Sorted._
import com.scala.fp.fundamentals.Curry._

object Test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(267); val res$0 = 

	factorial(10);System.out.println("""res0: Int = """ + $show(res$0));$skip(41); val res$1 = 
  
  // 0 1 1 2 3 5 8 13 21 34
 	fib(5);System.out.println("""res1: Int = """ + $show(res$1));$skip(9); val res$2 = 
  fib(1);System.out.println("""res2: Int = """ + $show(res$2));$skip(9); val res$3 = 
  fib(6);System.out.println("""res3: Int = """ + $show(res$3));$skip(8); val res$4 = 
	fib(7);System.out.println("""res4: Int = """ + $show(res$4));$skip(9); val res$5 = 
	fib(10);System.out.println("""res5: Int = """ + $show(res$5));$skip(55); val res$6 = 
	
	isSorted(Array(1,2,3), (a: Int, b: Int) => a <= b);System.out.println("""res6: Boolean = """ + $show(res$6));$skip(59); val res$7 = 
  
  isSorted(Array(1,2,3,7), (a: Int, b: Int) => a <= b);System.out.println("""res7: Boolean = """ + $show(res$7));$skip(55); val res$8 = 
  isSorted(Array(1,2,3,0), (a: Int, b: Int) => a <= b);System.out.println("""res8: Boolean = """ + $show(res$8));$skip(97); 
                                                  
  val fc = curry((a: Int, b : Int) => a + b);System.out.println("""fc  : Int => (Int => Int) = """ + $show(fc ));$skip(12); val res$9 = 

	fc(3)(2);System.out.println("""res9: Int = """ + $show(res$9));$skip(30); 
	
	val f = (x: Int) => x * x;System.out.println("""f  : Int => Int = """ + $show(f ));$skip(29); 

	val g = (y: Int) => y + 1;System.out.println("""g  : Int => Int = """ + $show(g ));$skip(24); 
	
	val fg = comp(f, g);System.out.println("""fg  : Int => Int = """ + $show(fg ));$skip(9); val res$10 = 

	fg(2);System.out.println("""res10: Int = """ + $show(res$10))}
}
