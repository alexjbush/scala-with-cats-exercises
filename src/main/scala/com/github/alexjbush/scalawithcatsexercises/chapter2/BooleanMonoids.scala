package com.github.alexjbush.scalawithcatsexercises.chapter2

object BooleanMonoids {

  val booleanAnd: MyMonoid[Boolean] = new MyMonoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  val booleanOr: MyMonoid[Boolean] = new MyMonoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  val booleanXor: MyMonoid[Boolean] = new MyMonoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x ^ y
  }

  val booleanXnor: MyMonoid[Boolean] = new MyMonoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x == y
  }

}
