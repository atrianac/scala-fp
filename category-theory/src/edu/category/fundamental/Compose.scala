package edu.category.fundamental


class Compose[T] {
  
    def identity[T](x: T) = x
    
    def compose(f: T => T, g: T => T) = (x: T) => g(f(x))

}