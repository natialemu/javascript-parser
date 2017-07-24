/**
  * Created by Nathnael on 4/28/2017.
  */

import scala.util.parsing.combinator.JavaTokenParsers




import scala.util.parsing.combinator.JavaTokenParsers

object CombinatorParser extends JavaTokenParsers{
  /** expr ::= term { { "+" | "-" } term }* */
  def expr: Parser[Statement] =
    term ~! opt(("+" | "-") ~ term) ^^ {
      case l ~ None          => l
      case l ~ Some("+" ~ r) => Plus(l, r)
      case l ~ Some("-" ~ r) => Minus(l, r)

    }

  /** term ::= factor { { "*" | "/" | "%" } factor }* */
  def term: Parser[Statement] =
    factor ~! opt(("*" | "/" | "%") ~ factor) ^^ {
      case l ~ None          => l
      case l ~ Some("*" ~ r) => Times(l, r)
      case l ~ Some("/" ~ r) => Div(l, r)
      case l ~ Some("%" ~ r) => Mod(l, r)
    }

  /** factor ::= wholeNumber | "+" factor | "-" factor | "(" expr ")" */
  def factor: Parser[Statement] = (
    wholeNumber ^^ { case s => Constant(s.toInt) }
      | "+" ~ factor ^^ { case _~e => e }
      | "-" ~ factor ^^ { case _~e => UMinus(e) }
      | "(" ~ expr ~ ")" ^^ { case _ ~ e ~ _ => e }
      | ident ^^{case s => Variable(s)}
      | reciever~ ident^^{case r ~ f=> Selection(r,f) }
      | struct
    )

  def struct: Parser[Statement] =
    "{" ~ "}"^^{case _~_ => Struct()} | "{" ~> repsep(field,",") <~ "}" ^^{case f => Struct(f:_*)}


  def field: Parser[Field] = ident ~ ":" ~ expr ^^{case i ~_~ e => Field(i,e)}
  def reciever: Parser[Statement] =
    rep(ident <~ ".")^^{case ss => Reciever(ss:_*)}



  def statement: Parser[Statement] = (
    expr <~ ";"^^{case e => e} |
      assignment^^{ case a=> a}

      | conditional^^{case c=> c}
      | loop ^^ { case l => l }
      | block^^{case g=> g}
    )

  def assignment: Parser[Statement]=
    factor ~ "=" ~  expr <~ ";" ^^{
      case i ~ _ ~ e => Assign(i,e)
    }



  def conditional: Parser[Statement] =
    "if" ~ "(" ~ expr ~ ")" ~ block ~! opt(("else" ~ block)) ^^ {
      case _ ~ _ ~ e ~ _ ~ b ~ Some("else" ~ c) => ComplexCond(e, b, c)
      case  _ ~ _ ~ e ~ _ ~ b ~ None => Cond(e, b)
    }




  def loop: Parser[Statement] =
    "while" ~ "(" ~ expr ~ ")" ~ block ^^{
      case _ ~ _ ~ e ~ _ ~ b => Loop(e,b)

    }

  def block: Parser[Statement] =
    "{" ~> rep1(statement) <~ "}" ^^{
      case ss => Block(ss:_*)

    }



}

