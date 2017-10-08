
/**
  * Created by Nathnael on 5/4/2017.
  */
import TestFixtures._
object mainStore {
  val store = Map[String, Cell](
    "x" -> Cell(5),
    "i" -> Cell(0),
    "r" -> Cell(0),
    "y" -> Cell(7)
  )

  def main(args: Array[String]): Unit ={
    println(store)
    Execute(store)(simpleAssignmentParsed)
    println(store)
    Execute(store)(statementParsed)
    println(store)
    println(store)
    Execute(store)(simpleStatementParsed)
    println("---------------- after if statement-------------")
    println(store)
  }

}
