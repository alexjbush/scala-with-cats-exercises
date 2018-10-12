package com.github.alexjbush.scalawithcatsexercises.chapter1

import org.scalatest.{FlatSpec, Matchers}

class PrintableSpec extends FlatSpec with Matchers {

  it should "Provide the format function for Cat" in {

    Printable.format(Cat("Ben", 2, "mustard")) should be("Ben is a 2 year-old mustard cat.")

  }

  it should "Provide the print function for Cat" in {

    Printable.print(Cat("Ben", 2, "mustard"))

  }

  it should "Provide an implicit format function for Cat objects" in {
    import PrintableSyntax._

    Cat("Ben", 2, "mustard").format should be("Ben is a 2 year-old mustard cat.")

  }

}
