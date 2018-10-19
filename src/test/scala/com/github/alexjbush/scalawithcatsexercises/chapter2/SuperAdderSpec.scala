package com.github.alexjbush.scalawithcatsexercises.chapter2

import cats.instances.int._
import cats.instances.option._
import cats.kernel.Monoid
import org.scalatest.{FlatSpec, Matchers}

class SuperAdderSpec extends FlatSpec with Matchers {

  it should "Add together Ints" in {
    SuperAdder.add(List(1, 2, 3)) should be(6)
    SuperAdder.add(List[Int]()) should be(0)
  }

  it should "Add together Options of Ints" in {
    val input1: List[Option[Int]] = List(Some(1), Some(2), Some(3))
    SuperAdder.add(input1) should be(Some(6))
    SuperAdder.add(List(Some(1), None, Some(3))) should be(Some(4))
    SuperAdder.add(List[Option[Int]]()) should be(None)
  }

  it should "Create and empty Order and add two orders together" in {

    Monoid[Order].empty should be(Order(0, 0))

    SuperAdder.add(List(Order(1, 1), Order(2, 2))) should be(Order(3, 3))

  }

}
