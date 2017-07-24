name := "mini-js-parser"

version := "1.0"

scalaVersion := "2.12.1"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.5",
  "org.parboiled"          %% "parboiled"                % "2.1.4",
  "org.scalatest"          %% "scalatest"                % "3.0.1" % Test
)

    