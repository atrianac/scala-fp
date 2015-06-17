package chapter3

object Chapter3 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(246); 
  
  val x = List(1,2,3,4,5) match {
  	case Cons(x, Cons(2, Cons(4, _))) => x
  	case Nil => 42
  	case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
  	case Cons(h, t) => h + List.sum(t)
  	case _ => 101
  };System.out.println("""x  : Int = """ + $show(x ));$skip(29); 
  
  val y = List(1,2,3,4,5);System.out.println("""y  : chapter3.List[Int] = """ + $show(y ));$skip(18); val res$0 = 
  
  List.tail(y);System.out.println("""res0: chapter3.List[Int] = """ + $show(res$0));$skip(24); val res$1 = 
  
  List.setHead(6, y);System.out.println("""res1: chapter3.Cons[Int] = """ + $show(res$1));$skip(22); val res$2 = 
  
  List.drop(y, -1);System.out.println("""res2: chapter3.List[Int] = """ + $show(res$2));$skip(18); val res$3 = 
  
  List.init(y);System.out.println("""res3: chapter3.List[Int] = """ + $show(res$3));$skip(18); val res$4 = 
  
  List.sum2(y);System.out.println("""res4: Int = """ + $show(res$4));$skip(18); val res$5 = 
  
  List.sum3(y);System.out.println("""res5: Int = """ + $show(res$5));$skip(22); val res$6 = 
  
  List.product2(y);System.out.println("""res6: Double = """ + $show(res$6));$skip(22); val res$7 = 
  
  List.product3(y);System.out.println("""res7: Double = """ + $show(res$7));$skip(22); val res$8 = 
  
  List.product4(y);System.out.println("""res8: Double = """ + $show(res$8));$skip(33); 
  
  val z = List(1,2, 0, 3,4,5);System.out.println("""z  : chapter3.List[Int] = """ + $show(z ));$skip(22); val res$9 = 
  
  List.product3(z);System.out.println("""res9: Double = """ + $show(res$9));$skip(59); val res$10 = 
  
 	List.foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_));System.out.println("""res10: chapter3.List[Int] = """ + $show(res$10));$skip(129); val res$11 = 
  
  List.foldRight(List(List(1,2,3),List(4,5,6),List(7,8,9)), Nil:List[Int])((a,b) => List.foldRight(a, b)((a,b) =>Cons(a,b)) );System.out.println("""res11: chapter3.List[Int] = """ + $show(res$11));$skip(66); val res$12 = 
 	
 	List.foldLeft(List(1,2,3), Nil:List[Int])((a,b) =>Cons(b,a));System.out.println("""res12: chapter3.List[Int] = """ + $show(res$12));$skip(20); val res$13 = 
  
  List.lenght(z);System.out.println("""res13: Int = """ + $show(res$13));$skip(21); val res$14 = 
  
  List.reverse(y);System.out.println("""res14: chapter3.List[Int] = """ + $show(res$14));$skip(23); val res$15 = 
  
  List.append(y, 9);System.out.println("""res15: chapter3.List[Int] = """ + $show(res$15));$skip(108); val res$16 = 
                                                  
  List.concat(List(List(1,2,3),List(4,5,6),List(7,8,9)));System.out.println("""res16: chapter3.List[Int] = """ + $show(res$16));$skip(16); val res$17 = 
	List.addOne(y);System.out.println("""res17: chapter3.List[Int] = """ + $show(res$17));$skip(31); 
	
	
	val d = List(1.0,2.0,3.0);System.out.println("""d  : chapter3.List[Double] = """ + $show(d ));$skip(27); val res$18 = 
	
	List.convertToString(d);System.out.println("""res18: chapter3.List[String] = """ + $show(res$18))}
}
