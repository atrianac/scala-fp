package edu.category.fundamentas

object Fundamentals {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(94); 
  
  	val compose = new Compose[Int]();System.out.println("""compose  : edu.category.fundamentas.Compose[Int] = """ + $show(compose ));$skip(39); 
  	
    val square = (x: Int) => x * 2;System.out.println("""square  : Int => Int = """ + $show(square ));$skip(66); 
    
    val instComp = compose.compose(square, compose.identity);System.out.println("""instComp  : Int => Int = """ + $show(instComp ));$skip(113); 
                                                  
    val instComp2 = compose.compose(compose.identity, square);System.out.println("""instComp2  : Int => Int = """ + $show(instComp2 ));$skip(34); val res$0 = 
    
    instComp(2) == square(2);System.out.println("""res0: Boolean = """ + $show(res$0));$skip(35); val res$1 = 
    
    instComp2(2) == square(2);System.out.println("""res1: Boolean = """ + $show(res$1));$skip(56); 
 		
 		def squareMemoize = Memoize( (x: Int) => x * 2 );System.out.println("""squareMemoize: => edu.category.fundamentas.Memoize[Int,Int]""");$skip(21); val res$2 = 
    squareMemoize(2);System.out.println("""res2: Int = """ + $show(res$2));$skip(21); val res$3 = 
    squareMemoize(8);System.out.println("""res3: Int = """ + $show(res$3));$skip(35); 
    
    val r = scala.util.Random;System.out.println("""r  : util.Random.type = """ + $show(r ));$skip(38); 
    val rs = new scala.util.Random(2);System.out.println("""rs  : scala.util.Random = """ + $show(rs ));$skip(76); 
    
    def randomMemoize = Memoize((r: scala.util.Random) => r.nextInt());System.out.println("""randomMemoize: => edu.category.fundamentas.Memoize[scala.util.Random,Int]""");$skip(21); val res$4 = 
    randomMemoize(r);System.out.println("""res4: Int = """ + $show(res$4));$skip(21); val res$5 = 
    randomMemoize(r);System.out.println("""res5: Int = """ + $show(res$5));$skip(21); val res$6 = 
    randomMemoize(r);System.out.println("""res6: Int = """ + $show(res$6));$skip(22); val res$7 = 
    randomMemoize(rs);System.out.println("""res7: Int = """ + $show(res$7));$skip(22); val res$8 = 
    randomMemoize(rs);System.out.println("""res8: Int = """ + $show(res$8))}
}
