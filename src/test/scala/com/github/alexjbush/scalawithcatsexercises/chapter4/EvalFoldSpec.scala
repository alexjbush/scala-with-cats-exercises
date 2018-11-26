package com.github.alexjbush.scalawithcatsexercises.chapter4

import org.scalatest.{FlatSpec, Matchers}

class EvalFoldSpec extends FlatSpec with Matchers {

  def testList: List[Unit] = List.fill(10000)(Unit)

  val acc: Unit = Unit

  def fn: (Unit, Unit) => Unit = (_, _) => Unit

  it should "Throw a StackOverflowError exception using the unsafe foldRight" in {
    intercept[StackOverflowError] {
      EvalFold.foldRightStackUnsafe(testList, acc)(fn)
    }
  }

  it should "Not throw an exception using stack-safe foldRight" in {
    EvalFold.foldRightStackSafe(testList, acc)(fn).value should be(())
  }

  it should "Not throw an exception using foldRight" in {
    EvalFold.foldRight(testList, acc)(fn) should be(())
  }

}
