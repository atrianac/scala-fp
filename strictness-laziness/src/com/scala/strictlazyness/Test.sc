package com.scala.strictlazyness

object Test {
 
 val s = Stream(1,2,3,4,5,6,7,8,9)                //> s  : com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
 
 val s1 = Stream(10,11,12)                        //> s1  : com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
 
 Stream.cons(2, s)                                //> res0: com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
 
 s toList                                         //> res1: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
 
 s take(4)                                        //> res2: List[Int] = List(1, 2, 3, 4)
 
 s drop(4)                                        //> res3: List[Int] = List(5, 6, 7, 8, 9)
 
 s takeWhile ( _ < 5 ) toList                     //> res4: List[Int] = List(1, 2, 3, 4)
 
 s exist ( _ % 2 == 0 )                           //> res5: Boolean = true
 
 s forAll (_ < 10)                                //> res6: Boolean = true
	
 s forAll (_ < 9)                                 //> res7: Boolean = false
 
 s takeWhile_1 ( _ < 5) toList                    //> res8: List[Int] = List(1, 2, 3, 4)
 
 s headOption                                     //> res9: Option[Int] = Some(1)
 
 s headOption_1                                   //> res10: Option[Int] = Some(1)
 
 s map ( _ + 1 ) take (2)                         //> res11: List[Int] = List(2, 3)
 
 s append (s1) toList                             //> res12: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
 
 s filter (_ % 2 == 0) toList                     //> res13: List[Int] = List(2, 4, 6, 8)
}