

/**
  * Created by Nathnael on 4/25/2017.
  *
  */

object TestFixtures {

  val s =
    Block(
      Assign(Variable("r"), Constant(1)),
      Loop(
        Minus(Variable("x"), Variable("i")),
        Block(
          Assign(Variable("i"), Constant(1)),
          Assign(Variable("r"), Variable("i")))
      )
    )


  val complex1 =
    Div(
      Minus(
        Plus(
          Constant(1),
          Constant(2)
        ),
        Times(
          Constant(3),
          Constant(4)
        )
      ),
      Constant(5)
    );

  val complex1string = "((1 + 2) - (3 * 4)) / 5;"

  val complex1string2 = "  ((1 + 2) - (3 * 4)) / 5  ;"

  val complex2 =
    Mod(
      Minus(
        Plus(
          Constant(1),
          Constant(2)
        ),
        Times(
          UMinus(
            Constant(3)
          ),
          Constant(4)
        )
      ),
      Constant(5)
    );

  val loopAndConditional = "if((-3+4)+(5*6)){while(0){x=3;y=5;{xy=88;}}}"
  val loopAndConditionalParsed =
    ComplexCond(
      Plus(
        Plus(Constant(-3),Constant(4)),
        Times(Constant(5),Constant(6))

      ),
      Block(Loop(
        Constant(0),
        Block(
          Assign(Variable("x"),Constant(3)
          ),
          Assign(Variable("y"),Constant(5)
          ),
          Block(
            Assign(Variable("xy"),Constant(88))
          )
        )
      )

      ),
      Block()
    )

  val simpleAssignment = "x = y ;"
  val assignment = "x = 5 ; y = 7;"
  val expression = "((1 + y2) - (3 * y4)) / 5;"
  val complexAssignment = "x = ((1 + y2) - (3 * y4)) / 5;"
  val simpleConditional = "if (y) { x = 2; }"
  val simpleCompoundConditional = "if (1) { x = 2; } else { x = 3; }"
  val simpleBlock = "{ r = r + x; y = y + 1 ; }"
  val simpleStatement = "if (x) { r = r + x; y = y + 1; }"
  val statement = "while (y) { r = r + x; y = y - 1; }"
  val ComplexSatement = "while (y) { r = r + x ; y = y - 1 ;}"

  val simpleStatementParsed =
    Cond(
      Variable("x"),
      Block(
        Assign(
          Variable("r"),
          Plus(Variable("r"),Variable("x"))
        ),
        Assign(
          Variable("y"),
          Plus(Variable("y"),Constant(1))
        )
      )
    )

  val simpleConditionalParsed =
    Cond(
      Variable("y"),
      Block(
        Assign(Variable("x"),Constant(2))
      )
    )



  val statementParsed =
    Loop(
      Variable("y"),
      Block(
        Assign(Variable("r"),
          Plus(Variable("r"),Variable("x"))),
        Assign(Variable("y"),
          Minus(Variable("y"),Constant(1)))

      )
    )


  val simpleAssignmentParsed =
    Assign(Variable("x"),Variable("y"))
  val assignmentParsed =
    Block(
      Assign(Variable("x"),Constant(5)),
      Assign(Variable("y"),Constant(7))
    )
  /*val expressionParsed =
    Block(
      Minus(
        Plus(),
        Div(
          Times(Constant(3),),
          Constant(5)
        )
      ))*/





}