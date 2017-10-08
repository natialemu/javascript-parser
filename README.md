# javascript-parser

## Overview

This is a scala-based javascript parser that builds an abstract syntax tree for different constructs in Javascript as well as the dynamic language semantics such as assignments, variables, conditionals and loops as part of the evaluator. It also has support for constructs such as structure creation, field creation, assignment to an existing field in a structure, and addition of a new field to a structure. There is also support for named states, where a mutable store or memory maps named variables to storage cells where values can be stored. 

## Tools/Frameworks

- Scala
- Sbt build tool
- [Matryoshka](https://github.com/slamdata/matryoshka) Generalized folds, unfolds, and traversals for fixed point data structures in Scala. 

## Grammar

`expression ::= term { { "+" | "-" } term }*  `   
`term       ::= factor { { "*" | "/" | "%" } factor }* `
`factor ::= ident { "." ident }* | number | "+" factor | "-" factor | "(" expr ")" | struct `

`statement   ::= expression ";" | assignment | conditional | loop | block`
`assignment  ::= ident { "." ident }* "=" expression ";"`
`conditional ::= "if" "(" expression ")" block [ "else" block ]`
`loop        ::= "while" "(" expression ")" block`
`block       ::= "{" statement* "}"`

`struct ::= "{" "}" | "{" field { "," field }* "}"`
`field  ::= ident ":" expr`


## Tests



