package chapter3

object Chapter3 {
  
  val x = List(1,2,3,4,5) match {
  	case Cons(x, Cons(2, Cons(4, _))) => x
  	case Nil => 42
  	case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
  	case Cons(h, t) => h + List.sum(t)
  	case _ => 101
  }                                               //> x  : Int = 3
  
  val y = List(1,2,3,4,5)                         //> y  : chapter3.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Nil)))))
  
  List.tail(y)                                    //> res0: chapter3.List[Int] = Cons(2,Cons(3,Cons(4,Cons(5,Nil))))
  
  List.setHead(6, y)                              //> res1: chapter3.Cons[Int] = Cons(6,Cons(2,Cons(3,Cons(4,Cons(5,Nil)))))
  
  List.drop(y, -1)                                //> res2: chapter3.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Nil)))))
  
  List.init(y)                                    //> res3: chapter3.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Nil))))
  
  List.sum2(y)                                    //> res4: Int = 15
  
  List.sum3(y)                                    //> res5: Int = 15
  
  List.product2(y)                                //> res6: Double = 120.0
  
  List.product3(y)                                //> res7: Double = 120.0
  
  List.product4(y)                                //> res8: Double = 120.0
  
  val z = List(1,2, 0, 3,4,5)                     //> z  : chapter3.List[Int] = Cons(1,Cons(2,Cons(0,Cons(3,Cons(4,Cons(5,Nil)))))
                                                  //| )
  
  List.product3(z)                                //> res9: Double = 0.0
  
 	List.foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_))
                                                  //> res10: chapter3.List[Int] = Cons(1,Cons(2,Cons(3,Nil)))
  
  List.foldRight(List(List(1,2,3),List(4,5,6),List(7,8,9)), Nil:List[Int])((a,b) => List.foldRight(a, b)((a,b) =>Cons(a,b)) )
                                                  //> res11: chapter3.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Cons(6,Cons(7
                                                  //| ,Cons(8,Cons(9,Nil)))))))))
 	
 	List.foldLeft(List(1,2,3), Nil:List[Int])((a,b) =>Cons(b,a))
                                                  //> res12: chapter3.List[Int] = Cons(3,Cons(2,Cons(1,Nil)))
  
  List.lenght(z)                                  //> res13: Int = 6
  
  List.reverse(y)                                 //> res14: chapter3.List[Int] = Cons(5,Cons(4,Cons(3,Cons(2,Cons(1,Nil)))))
  
  List.append(y, 9)                               //> res15: chapter3.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Cons(9,Nil)))
                                                  //| )))
                                                  
  List.concat(List(List(1,2,3),List(4,5,6),List(7,8,9)))
                                                  //> res16: chapter3.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Cons(6,Cons(7
                                                  //| ,Cons(8,Cons(9,Nil)))))))))
	List.addOne(y)                            //> res17: chapter3.List[Int] = Cons(2,Cons(3,Cons(4,Cons(5,Cons(6,Nil)))))
	
	
	val d = List(1.0,2.0,3.0)                 //> d  : chapter3.List[Double] = Cons(1.0,Cons(2.0,Cons(3.0,Nil)))
	
	List.convertToString(d)                   //> res18: chapter3.List[String] = Cons(1.0,Cons(2.0,Cons(3.0,Nil)))
}