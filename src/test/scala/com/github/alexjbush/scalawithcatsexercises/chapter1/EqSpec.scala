package com.github.alexjbush.scalawithcatsexercises.chapter1

import cats._
import cats.implicits._
import org.scalatest.{FlatSpec, Matchers}

class EqSpec extends FlatSpec with Matchers {

  it should "Compare the equality of Cats" in {

    val cat1 = Cat("Garfield", 38, "orange and black")
    val cat2 = Cat("Heathcliff", 33, "orange and black")

    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]

    val catEq = Eq[Cat]

    catEq.eqv(cat1, cat1) should be(true)
    cat1 =!= cat1 should be(false)
    catEq.eqv(cat1, cat2) should be(false)
    cat1 =!= cat2 should be(true)

    val optionCatEq = Eq[Option[Cat]]
    optionCatEq.eqv(optionCat1, optionCat1) should be(true)
    optionCat1 =!= optionCat1 should be(false)
    optionCatEq.eqv(optionCat1, optionCat2) should be(false)
    optionCat1 =!= optionCat2 should be(true)

  }


}
