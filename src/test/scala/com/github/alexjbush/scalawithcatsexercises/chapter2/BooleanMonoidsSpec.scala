package com.github.alexjbush.scalawithcatsexercises.chapter2

import org.scalatest.{FlatSpec, Matchers}

class BooleanMonoidsSpec extends FlatSpec with Matchers{

  it should "Obey the laws for Boolean Add" in {
    booleanLaws(BooleanMonoids.booleanAnd)
  }

  it should "Obey the laws for Boolean Or" in {
    booleanLaws(BooleanMonoids.booleanOr)
  }

  it should "Obey the laws for Boolean Xor" in {
    booleanLaws(BooleanMonoids.booleanXor)
  }

  it should "Obey the laws for Boolean Xnor" in {
    booleanLaws(BooleanMonoids.booleanXnor)
  }

  def booleanLaws(implicit monoid: MyMonoid[Boolean]): Unit = {
    MyMonoid.associativeLaw(true, false, true) should be (true)
    MyMonoid.associativeLaw(false, true, false) should be (true)
    MyMonoid.identityLaw(true)
    MyMonoid.identityLaw(false)
  }

}
