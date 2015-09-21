package com.scala.strictlazyness

object Test {
 
 val s = Stream(1,2,3,4,5,6,7,8,9)                //> s  : com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
 
 val s1 = Stream(10,11,12)                        //> s1  : com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
 
 def ones: Stream[Int] = Stream.cons(1, ones)     //> ones: => com.scala.strictlazyness.Stream[Int]
 
 Stream.cons(2, s)                                //> res0: com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
 
 s toList                                         //> res1: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
 
 s take(4)                                        //> res2: List[Int] = List(1, 2, 3, 4)
 
 s take_unfold(4)                                 //> res3: List[Int] = List(1, 2, 3, 4)
 
 s drop(4)                                        //> res4: List[Int] = List(5, 6, 7, 8, 9)
 
 s takeWhile ( _ < 5 ) toList                     //> res5: List[Int] = List(1, 2, 3, 4)
 
 s exist ( _ % 2 == 0 )                           //> res6: Boolean = true
 
 s forAll (_ < 10)                                //> res7: Boolean = true
	
 s forAll (_ < 9)                                 //> res8: Boolean = false
 
 s takeWhile_1 ( _ < 5) toList                    //> res9: List[Int] = List(1, 2, 3, 4)
 
 s headOption                                     //> res10: Option[Int] = Some(1)
 
 s headOption_1                                   //> res11: Option[Int] = Some(1)
 
 s map ( _ + 1 ) take (2)                         //> res12: List[Int] = List(2, 3)
 
 s append (s1) toList                             //> res13: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
 
 s filter (_ % 2 == 0) toList                     //> res14: List[Int] = List(2, 4, 6, 8)
 
 ones take(5) toList                              //> res15: List[Int] = List(1, 1, 1, 1, 1)
 
 ones exist (_ % 2 != 0)                          //> res16: Boolean = true
 
 ones map (_ + 1) exist (_ % 2 == 0)              //> res17: Boolean = true
 
 ones takeWhile (_ == 1) take (2)                 //> res18: List[Int] = List(1, 1)
 
 ones forAll (_ != 1)                             //> res19: Boolean = false
 
 val s2 = Stream.constant(1)                      //> s2  : com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
	
 val s2_1 = Stream.constant_1(1)                  //> s2_1  : com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
                                                  //| 
 s2 take (5)                                      //> res20: List[Int] = List(1, 1, 1, 1, 1)

 s2_1 take (5)                                    //> res21: List[Int] = List(1, 1, 1, 1, 1)
  
 val s3 = Stream.from(4)                          //> s3  : com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
 
 s3 take (4)                                      //> res22: List[Int] = List(4, 5, 6, 7)
 
 Stream.fibs.take(10)                             //> res23: List[Int] = List(1, 1, 2, 3, 5, 8, 13, 21, 34, 55)
 
 Stream.fibs_1.take(10)                           //> res24: List[Int] = List(1, 1, 2, 3, 5, 8, 13, 21, 34, 55)

 val s4 = Stream.from_1(4)                        //> s4  : com.scala.strictlazyness.Stream[Int] = Cons(<function0>,<function0>)
 
 s4 take (4)                                      //> res25: List[Int] = List(4, 5, 6, 7)
}