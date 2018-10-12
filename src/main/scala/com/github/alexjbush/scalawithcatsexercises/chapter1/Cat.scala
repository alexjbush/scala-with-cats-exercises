package com.github.alexjbush.scalawithcatsexercises.chapter1

import cats._
import cats.implicits._

final case class Cat(name: String, age: Int, color: String)

object Cat {

  import PrintableInstances._

  implicit val catPrinter: Printable[Cat] = new Printable[Cat] {
    override def format(value: Cat): String = catBitsToString(Printable.format(value.name), Printable.format(value.age), Printable.format(value.color))
  }

  def catBitsToString(name: String, age: String, color: String): String = s"$name is a $age year-old $color cat."

  implicit val catShow: Show[Cat] = Show.show(cat => catBitsToString(cat.name.show, cat.age.show, cat.color.show))

  implicit val catEq: Eq[Cat] = Eq.instance[Cat]((cat1, cat2) => cat1.name === cat2.name && cat1.age === cat2.age && cat1.color === cat2.color)
}