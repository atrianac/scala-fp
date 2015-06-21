package com.scala.exceptionhandling

import scala.util.Try



object Test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(124); 
  
  val lst = List(Some(1), Some(2), Some(3));System.out.println("""lst  : List[com.scala.exceptionhandling.Some[Int]] = """ + $show(lst ));$skip(45); 
  
  val lst2 = List(Some(1), Some(2), None);System.out.println("""lst2  : List[Product with Serializable with com.scala.exceptionhandling.Option[Int]] = """ + $show(lst2 ));$skip(26); val res$0 = 
  
	Option.sequence(lst);System.out.println("""res0: com.scala.exceptionhandling.Option[List[Int]] = """ + $show(res$0));$skip(25); val res$1 = 
	
	Option.sequence(lst2);System.out.println("""res1: com.scala.exceptionhandling.Option[List[Int]] = """ + $show(res$1));$skip(124); 
	
	def parseInt(s: Any): Option[Int] = {
		try {
			Some(s.toString().toInt)
		} catch {
			case _:Throwable => None
		}
	};System.out.println("""parseInt: (s: Any)com.scala.exceptionhandling.Option[Int]""");$skip(33); 
	
	val lst3 = List(1, 2, 3, "4");System.out.println("""lst3  : List[Any] = """ + $show(lst3 ));$skip(33); 
	
	val lst4 = List(1, 2, 3, "c");System.out.println("""lst4  : List[Any] = """ + $show(lst4 ));$skip(35); val res$2 = 
	
	Option.traverse(lst3)(parseInt);System.out.println("""res2: com.scala.exceptionhandling.Option[List[Int]] = """ + $show(res$2));$skip(34); val res$3 = 
  Option.traverse(lst4)(parseInt);System.out.println("""res3: com.scala.exceptionhandling.Option[List[Int]] = """ + $show(res$3))}
}
