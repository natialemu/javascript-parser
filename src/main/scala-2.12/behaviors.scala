
/**
  * Created by Nathnael on 4/11/2017.
  */
object behaviors {
  def toFormattedString(prefix: String)(e: Statement): String = e match {
    case Constant(c) => prefix + c.toString
    case Variable(name) => prefix + name
    case UMinus(r)   => buildUnaryExprString(prefix, "UMinus", toFormattedString(prefix + INDENT)(r))
    case Plus(l, r)  => buildExprString(prefix, "Plus", toFormattedString(prefix + INDENT)(l), toFormattedString(prefix + INDENT)(r))
    case Minus(l, r) => buildExprString(prefix, "Minus", toFormattedString(prefix + INDENT)(l), toFormattedString(prefix + INDENT)(r))
    case Times(l, r) => buildExprString(prefix, "Times", toFormattedString(prefix + INDENT)(l), toFormattedString(prefix + INDENT)(r))
    case Div(l, r)   => buildExprString(prefix, "Div", toFormattedString(prefix + INDENT)(l), toFormattedString(prefix + INDENT)(r))
    case Mod(l, r)   => buildExprString(prefix, "Mod", toFormattedString(prefix + INDENT)(l), toFormattedString(prefix + INDENT)(r))
    case Assign(l,r) => buildExprString(prefix, "Assign", toFormattedString(prefix + INDENT)(l), toFormattedString(prefix + INDENT)(r))
    case Cond(expr,block) => buildExprString(prefix, "If", toFormattedString(prefix + INDENT)(expr), toFormattedString(prefix + INDENT)(block))
    case Loop(guard,body) => buildExprString(prefix, "While", toFormattedString(prefix + INDENT)(guard), toFormattedString(prefix + INDENT)(body))
    case Block(statements@_*) =>{
      val formatted_statements = statements.foldLeft(List[String]()){case (fstatement,next)=>
        toFormattedString(prefix + EOL)(next) :: fstatement

      }

      buildStatementString(prefix, "Block", formatted_statements)
    }
  }

  def buildStatementString(prefix : String, nodeString: String, statements: List[String]): String ={

    val result = new StringBuilder(prefix)
    result.append(nodeString)
    result.append("(")
    result.append(EOL)
    result.append(statements(0))
    for(a <- 1 to statements.size){
      result.append(",")
      result.append(EOL)
      result.append(statements(a))
    }
    result.append(")")
    result.toString
  }
  def toFormattedString(e: Statement): String = toFormattedString("")(e)

  def buildExprString(prefix: String, nodeString: String, leftString: String, rightString: String) = {
    val result = new StringBuilder(prefix)
    result.append(nodeString)
    result.append("(")
    result.append(EOL)
    result.append(leftString)
    result.append(", ")
    result.append(EOL)
    result.append(rightString)
    result.append(")")
    result.toString
  }

  def buildUnaryExprString(prefix: String, nodeString: String, exprString: String) = {
    val result = new StringBuilder(prefix)
    result.append(nodeString)
    result.append("(")
    result.append(EOL)
    result.append(exprString)
    result.append(")")
    result.toString
  }
  val EOL = scala.util.Properties.lineSeparator
  val INDENT = ".."
}
