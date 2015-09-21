package edu.category.fundamentas

object Fundamentals {
  
  	val compose = new Compose[Int]()          //> compose  : edu.category.fundamentas.Compose[Int] = edu.category.fundamentas.C
                                                  //| ompose@28ba21f3
  	
    val square = (x: Int) => x * 2                //> square  : Int => Int = <function1>
    
    val instComp = compose.compose(square, compose.identity)
                                                  //> instComp  : Int => Int = <function1>
                                                  
    val instComp2 = compose.compose(compose.identity, square)
                                                  //> instComp2  : Int => Int = <function1>
    
    instComp(2) == square(2)                      //> res0: Boolean = true
    
    instComp2(2) == square(2)                     //> res1: Boolean = true
 		
 		def squareMemoize = Memoize( (x: Int) => x * 2 )
                                                  //> squareMemoize: => edu.category.fundamentas.Memoize[Int,Int]
    squareMemoize(2)                              //> res2: Int = 4
    squareMemoize(8)                              //> res3: Int = 16
    
    val r = scala.util.Random                     //> r  : util.Random.type = scala.util.Random$@3d24753a
    val rs = new scala.util.Random(2)             //> rs  : scala.util.Random = scala.util.Random@59a6e353
    
    def randomMemoize = Memoize((r: scala.util.Random) => r.nextInt())
                                                  //> randomMemoize: => edu.category.fundamentas.Memoize[scala.util.Random,Int]
    randomMemoize(r)                              //> res4: Int = -1357766841
    randomMemoize(r)                              //> res5: Int = -1562196628
    randomMemoize(r)                              //> res6: Int = 62645598
    randomMemoize(rs)                             //> res7: Int = -1154715079
    randomMemoize(rs)                             //> res8: Int = 1260042744
}