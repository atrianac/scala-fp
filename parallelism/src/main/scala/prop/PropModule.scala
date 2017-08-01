package prop

/**
  * Created by atriana on 5/23/17.
  */
object PropModule {

  trait Prop {
    def check: Unit
    def &&(p: Prop) : Prop = ???
  }

}
