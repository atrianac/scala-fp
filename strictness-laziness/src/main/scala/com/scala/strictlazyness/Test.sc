package com.scala.strictlazyness

object Test {
 
 val s = Stream(1,2,3,4,5,6,7,8,9)                //> s  : com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
 
 val s1 = Stream(10,11,12)                        //> s1  : com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
 
 def ones: Stream[Int] = Stream.cons(1, ones)     //> ones: => com.scala.strictlazyness.Stream[Int]
 
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
 
 ones take(5) toList                              //> res14: List[Int] = List(1, 1, 1, 1, 1)
 
 ones exist (_ % 2 != 0)                          //> res15: Boolean = true
 
 ones map (_ + 1) exist (_ % 2 == 0)              //> res16: Boolean = true
 
 ones takeWhile (_ == 1) take (2)                 //> res17: List[Int] = List(1, 1)
 
 ones forAll (_ != 1)                             //> res18: Boolean = false
 
 val s2 = Stream.constant(1)                      //> s2  : com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
 
 s2 take (5)                                      //> res19: List[Int] = List(1, 1, 1, 1, 1)
 
 val s3 = Stream.from(4)                          //> s3  : com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
 
 s3 take (4)                                      //> res20: List[Int] = List(4, 5, 6, 7)
 
 Stream.fibs.take(10)                             //> res21: List[Int] = List(1, 1, 2, 3, 5, 8, 13, 21, 34, 55)
}