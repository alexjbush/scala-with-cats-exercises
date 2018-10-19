package com.github.alexjbush.scalawithcatsexercises.chapter2

import cats._
import cats.syntax.semigroup._

/*
 * Version v3.5a-32
 */
object SuperAdder {

  def add[A: Monoid](items: List[A]): A = {
    items.foldLeft(Monoid[A].empty)(_ |+| _)
  }

}

case class Order(totalCost: Double, quantity: Double)

object Order {
  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {

    import cats.instances.double._

    override def empty: Order = Order(Monoid[Double].empty, Monoid[Double].empty)

    override def combine(x: Order, y: Order): Order = Order(x.totalCost |+| y.totalCost, x.quantity |+| y.quantity)
  }
}