package edu.category.fundamental


class Memoize[-P, +R](f: P => R) extends (P => R) {
  
  import scala.collection.mutable
  private[this] val vals = mutable.Map.empty[P, R]
  
  def apply(p: P) : R = vals.getOrElseUpdate(p, f(p))
  
}

object Memoize {
   def apply[P, R](f: P => R) = new Memoize(f)
}