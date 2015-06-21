package com.scala.exceptionhandling

import scala.util.Try



object Test {
  
  val lst = List(Some(1), Some(2), Some(3))       //> lst  : List[com.scala.exceptionhandling.Some[Int]] = List(Some(1), Some(2), 
                                                  //| Some(3))
  
  val lst2 = List(Some(1), Some(2), None)         //> lst2  : List[Product with Serializable with com.scala.exceptionhandling.Opti
                                                  //| on[Int]] = List(Some(1), Some(2), None)
  
	Option.sequence(lst)                      //> res0: com.scala.exceptionhandling.Option[List[Int]] = Some(List(1, 2, 3))
	
	Option.sequence(lst2)                     //> res1: com.scala.exceptionhandling.Option[List[Int]] = None
	
	def parseInt(s: Any): Option[Int] = {
		try {
			Some(s.toString().toInt)
		} catch {
			case _:Throwable => None
		}
	}                                         //> parseInt: (s: Any)com.scala.exceptionhandling.Option[Int]
	
	val lst3 = List(1, 2, 3, "4")             //> lst3  : List[Any] = List(1, 2, 3, 4)
	
	val lst4 = List(1, 2, 3, "c")             //> lst4  : List[Any] = List(1, 2, 3, c)
	
	Option.traverse(lst3)(parseInt)           //> res2: com.scala.exceptionhandling.Option[List[Int]] = Some(List(1, 2, 3, 4))
                                                  //| 
  Option.traverse(lst4)(parseInt)                 //> res3: com.scala.exceptionhandling.Option[List[Int]] = None
}