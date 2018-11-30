package com.github.alexjbush.scalawithcatsexercises.chapter4

import org.scalatest.{FlatSpec, Matchers}

class FactorialSpec extends FlatSpec with Matchers {

  it should "Return a writer with the correct messages" in {
    val res = Factorial.factorial(5)
    res.value should be(120)
    res.written.toList should contain theSameElementsInOrderAs List("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6", "fact 4 24", "fact 5 120")
  }

  it should "Capture writer messages correctly when run concurrently" in {
    import scala.concurrent._
    import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.duration._

    val res = Await.result(Future.sequence(Vector(
      Future(Factorial.factorial(3)),
      Future(Factorial.factorial(3))
    )), 5.seconds)

    val written = List("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6")
    res.map(_.value) should contain theSameElementsInOrderAs List(6, 6)
    res.map(_.written.toList) should contain theSameElementsInOrderAs List(written, written)
  }

}
