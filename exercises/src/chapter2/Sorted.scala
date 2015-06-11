package chapter2

object Sorted {
  
  def isSorted[A] (as: Array[A], comp: (A, A) => Boolean) : Boolean = {
    def looping(i: Int) : Boolean = {
      if(i == (as.length - 1)) true
      else
        if (comp(as(i), as(i + 1))) looping(i + 1)
        else false
    }
    looping(0)
  }

}