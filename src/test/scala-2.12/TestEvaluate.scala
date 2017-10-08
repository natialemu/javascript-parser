
/**
  * Created by Nathnael on 4/25/2017.
  */
import  Cell.{apply,unapply}
import org.scalatest.FunSuite
import mainStore._
import TestFixtures._
class TestEvaluate extends FunSuite{

  /**
    * Created by Nathnael on 4/24/2017.
    */
  //val parsedStatement = CombinatorParser.parseAll(CombinatorParser.statement,statement)
  test("check evaluate before simple assign"){store.get("u") === Cell(8)}
  val parsedExpr = CombinatorParser.parseAll(CombinatorParser.statement, complex1string)

  test("parser works 1") { assert(parsedExpr.get == complex1) }
  val parsedsimpleAssign = CombinatorParser.parseAll(CombinatorParser.statement, simpleAssignment)
  val parsedstatement = CombinatorParser.parseAll(CombinatorParser.statement, statement)

  test("parser works for statement"){assert(parsedstatement.get == statementParsed)}


  test("parser works for simple assignment"){assert(parsedsimpleAssign.get == simpleAssignmentParsed)}

  Execute(store)(statementParsed)
  test("check evaluate after while loop"){store.get("r") == Cell(49)}
  //confirm the value of x from the store

}
