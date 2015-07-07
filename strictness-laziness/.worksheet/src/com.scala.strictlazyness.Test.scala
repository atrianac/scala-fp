package com.scala.strictlazyness

object Test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(84); 
 
 val s = Stream(1,2,3,4,5,6,7,8,9);System.out.println("""s  : com.scala.strictlazyness.Stream[Int] = """ + $show(s ));$skip(29); 
 
 val s1 = Stream(10,11,12);System.out.println("""s1  : com.scala.strictlazyness.Stream[Int] = """ + $show(s1 ));$skip(48); 
 
 def ones: Stream[Int] = Stream.cons(1, ones);System.out.println("""ones: => com.scala.strictlazyness.Stream[Int]""");$skip(21); val res$0 = 
 
 Stream.cons(2, s);System.out.println("""res0: com.scala.strictlazyness.Stream[Int] = """ + $show(res$0));$skip(12); val res$1 = 
 
 s toList;System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(13); val res$2 = 
 
 s take(4);System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(13); val res$3 = 
 
 s drop(4);System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(32); val res$4 = 
 
 s takeWhile ( _ < 5 ) toList;System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(26); val res$5 = 
 
 s exist ( _ % 2 == 0 );System.out.println("""res5: Boolean = """ + $show(res$5));$skip(21); val res$6 = 
 
 s forAll (_ < 10);System.out.println("""res6: Boolean = """ + $show(res$6));$skip(20); val res$7 = 
	
 s forAll (_ < 9);System.out.println("""res7: Boolean = """ + $show(res$7));$skip(33); val res$8 = 
 
 s takeWhile_1 ( _ < 5) toList;System.out.println("""res8: List[Int] = """ + $show(res$8));$skip(16); val res$9 = 
 
 s headOption;System.out.println("""res9: Option[Int] = """ + $show(res$9));$skip(18); val res$10 = 
 
 s headOption_1;System.out.println("""res10: Option[Int] = """ + $show(res$10));$skip(28); val res$11 = 
 
 s map ( _ + 1 ) take (2);System.out.println("""res11: List[Int] = """ + $show(res$11));$skip(24); val res$12 = 
 
 s append (s1) toList;System.out.println("""res12: List[Int] = """ + $show(res$12));$skip(32); val res$13 = 
 
 s filter (_ % 2 == 0) toList;System.out.println("""res13: List[Int] = """ + $show(res$13));$skip(23); val res$14 = 
 
 ones take(5) toList;System.out.println("""res14: List[Int] = """ + $show(res$14));$skip(27); val res$15 = 
 
 ones exist (_ % 2 != 0);System.out.println("""res15: Boolean = """ + $show(res$15));$skip(39); val res$16 = 
 
 ones map (_ + 1) exist (_ % 2 == 0);System.out.println("""res16: Boolean = """ + $show(res$16));$skip(36); val res$17 = 
 
 ones takeWhile (_ == 1) take (2);System.out.println("""res17: List[Int] = """ + $show(res$17));$skip(24); val res$18 = 
 
 ones forAll (_ != 1);System.out.println("""res18: Boolean = """ + $show(res$18));$skip(31); 
 
 val s2 = Stream.constant(1);System.out.println("""s2  : com.scala.strictlazyness.Stream[Int] = """ + $show(s2 ));$skip(15); val res$19 = 
 
 s2 take (5);System.out.println("""res19: List[Int] = """ + $show(res$19));$skip(27); 
 
 val s3 = Stream.from(4);System.out.println("""s3  : com.scala.strictlazyness.Stream[Int] = """ + $show(s3 ));$skip(15); val res$20 = 
 
 s3 take (4);System.out.println("""res20: List[Int] = """ + $show(res$20));$skip(24); val res$21 = 
 
 Stream.fibs.take(10);System.out.println("""res21: List[Int] = """ + $show(res$21))}
}