package com.github.alexjbush.scalawithcatsexercises.chapter4

import cats.syntax.flatMap._
import com.github.alexjbush.scalawithcatsexercises.chapter4.Tree._
import org.scalatest.{FlatSpec, Matchers}

class TreeSpec extends FlatSpec with Matchers {

  it should "Simple example" in {
    branch(leaf(100), leaf(200)).
      flatMap(x => branch(leaf(x - 1), leaf(x + 1))) should be(
      Branch(Branch(Leaf(99), Leaf(101)), Branch(Leaf(199), Leaf(201)))
    )
  }

}
