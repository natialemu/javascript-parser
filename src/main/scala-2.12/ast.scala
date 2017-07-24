import javax.swing.text.AbstractDocument.Content

/**
  * Created by Nathnael on 4/25/2017.
  */

sealed trait Statement
case class Constant(value: Int) extends Statement
abstract class UnaryStatement(expr: Statement) extends Statement { require { expr != null } }
case class UMinus(expr: Statement) extends UnaryStatement(expr)
abstract class BinaryStatement (left: Statement, right: Statement) extends Statement { require { (left != null) && (right != null) } }
case class Assign(left: Statement, right: Statement) extends BinaryStatement(left,right)
case class Cond(expr: Statement, block: Statement) extends Statement
case class Loop(guard: Statement, body: Statement) extends Statement
case class ComplexCond(guard: Statement, block: Statement, block2: Statement) extends Statement
case class Block(statements: Statement*) extends Statement
case class Plus(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Minus(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Times(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Div(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Mod(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Variable(name: String) extends Statement {
  require(name != null)
}

case class Reciever(reciever: String*) extends Statement

case class Selection(receiver: Statement, field: String) extends Statement {
  require(receiver != null)
  require(field != null)
}
case class Field(identifier: String, expression: Statement)
case class Struct(content: Field*) extends Statement{
  require(content != null)
  require(!content.contains(null))
}




