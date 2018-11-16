package com.github.alexjbush.scalawithcatsexercises.chapter4

import org.scalatest.{FlatSpec, Matchers}

class IdSpec extends FlatSpec with Matchers {

  it should "Testing the ID Monad" in {
    Id.idMonad.pure(1) shouldBe 1
    Id.idMonad.flatMap(1)(_ + 1) shouldBe 2
  }

}
