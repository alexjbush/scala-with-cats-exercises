package com.github.alexjbush.scalawithcatsexercises.chapter3

import cats.implicits._
import org.scalatest.{FlatSpec, Matchers}

class TreeSpec extends FlatSpec with Matchers {

  it should "map a tree structure from Int to Double" in {

    Branch(Leaf(1), Branch(Leaf(2), Leaf(3))).map(_.toString) should be(Branch(Leaf("1"), Branch(Leaf("2"), Leaf("3"))))
    Branch(Leaf(1), Branch(Leaf(2), Leaf(3))).map(_.toString) should not be Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))

  }

}
