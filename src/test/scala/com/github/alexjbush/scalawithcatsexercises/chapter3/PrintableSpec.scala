package com.github.alexjbush.scalawithcatsexercises.chapter3

import org.scalatest.{FlatSpec, Matchers}

class PrintableSpec extends FlatSpec with Matchers {

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }

  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if (value) "yes" else "no"
    }

  it should "have a printable implementation for string and boolean" in {

    Printable.format("hello") should be("\"hello\"")

    Printable.format(true) should be("yes")

  }

  it should "have a printable implementation for boxed string and boolean" in {

    Printable.format(Box("hello")) should be("\"hello\"")

    Printable.format(Box(true)) should be("yes")

  }


}
