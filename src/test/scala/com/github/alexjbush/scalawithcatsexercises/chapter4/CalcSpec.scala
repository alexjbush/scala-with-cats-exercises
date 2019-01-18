package com.github.alexjbush.scalawithcatsexercises.chapter4

import org.scalatest.{FlatSpec, Matchers}

class CalcSpec extends FlatSpec with Matchers {

  import Calc._

  "evalOne" should "evaluate a simple addition" in {

    evalOne("+").run(List(2, 1)).value should be((List(3), 3))

  }

  "evalOne" should "evaluate a simple subtraction" in {

    evalOne("-").run(List(2, 1)).value should be((List(-1), -1))

  }

  "evalOne" should "throw an exception for a stack too small" in {

    intercept[IllegalStateException] {
      evalOne("-").run(List(2)).value
    }

  }

  "evalOne" should "throw an exception for an unknown symbol" in {

    intercept[IllegalArgumentException] {
      evalOne("@").run(List(2, 1)).value
    }

  }

  "evalOne" should "add an element to the stack" in {

    evalOne("1").run(List(2)).value should be((List(1, 2), 1))

  }

  "evalAll" should "run a simple addition example" in {
    evalAll(List("2", "1", "+")).runA(List.empty).value should be(3)
  }

  "evalInput" should "run a complex example" in {
    evalInput("1 2 + 3 4 + *") should be(21)
  }

}
