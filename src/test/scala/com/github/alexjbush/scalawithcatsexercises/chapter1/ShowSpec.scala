package com.github.alexjbush.scalawithcatsexercises.chapter1

import cats.implicits._
import org.scalatest.{FlatSpec, Matchers}

class ShowSpec extends FlatSpec with Matchers {

  it should "Provide the show function on Cat" in {
    Cat("Ben", 2, "mustard").show should be("Ben is a 2 year-old mustard cat.")
  }

}
