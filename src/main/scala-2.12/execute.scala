
/**
  * Created by Nathnael on 4/25/2017.
  */
import Execute.{Instance, Value}

import scala.collection.immutable.Map







/**
  * A cell for storing a value (either a number or an object).
  */
case class Cell(var value: Value) {
  def get = value
  def set(value: Value) = { this.value = value; this }
}

/**
  * A companion object defining a useful Cell instance.
  */
object Cell {
  def apply(i: Int): Cell = Cell(Left(i)) // Left -> number, Right -> object
  def apply(j: Instance): Cell = Cell(Right(j))
  val NULL = Cell(0)
}


/** An interpreter for expressions and statements. */
object Execute {
  type Store = Map[String, Cell]

  /**
    * An object (instance) is the same as a memory store.
    */
  type Instance = Store

  /**
    * A run-time value is either a number or an object.
    */
  type Value = Either[Int, Instance]



  def apply(store: Store)(s: Statement): Cell = s match {
    case Constant(value)    => Cell(Left(value))
    case Plus(left, right)  => binaryOperation(store,left,right,_+_)
    case Minus(left, right) => binaryOperation(store,left,right,_-_)
    case UMinus(expr)       => unaryOperation(store,expr,0-_)
    case Times(left, right) => binaryOperation(store,left,right,_*_)
    case Div(left, right)   => binaryOperation(store,left,right,_*_)
    case Mod(left, right)   => binaryOperation(store,left,right,_%_)
    case Variable(name)     => store(name)
    case Assign(left, right) => {
      val rvalue = apply(store)(right)
      val lvalue = apply(store)(left)
      lvalue.set(rvalue.get)
    }
    case Block(statements @ _*) =>
      statements.foldLeft(Cell.NULL)((c, s) => apply(store)(s))
    case Cond(expr,block) => {
      val exprvalue = apply(store)(expr)
      if(exprvalue.get.left.get != 0 || exprvalue.get.isRight){
        apply(store)(block)
      }
      Cell.NULL
    }
    case Loop(guard, body) => {
      var gvalue = apply(store)(guard)
      while (gvalue.get.left.get != 0 || gvalue.get.isRight) {
        apply(store)(body)
        gvalue = apply(store)(guard)
      }
      Cell.NULL
    }
    case Selection(record,field)=>{
      apply(store)(record).get.right.get.apply(field)
    }
    case Struct(fields@_*) =>{
      val ms = Map(fields.map(field =>(field.identifier,apply(store)(field.expression))):_*)

      Cell(ms)


    }
  }

  def unaryOperation(store: Store, left: Statement, operator: (Int) => Int): Cell = {
    val l: Int = apply(store)(left).get.left.get
    Cell(Left(operator(-l)))
  }
  def binaryOperation(store: Store, left: Statement, right: Statement, operator: (Int, Int) => Int): Cell = {
    val l: Int = apply(store)(left).get.left.get
    val r: Int = apply(store)(right).get.left.get
    Cell(Left(operator(l, r)))
  }
}