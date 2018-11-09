package com.github.alexjbush.scalawithcatsexercises.chapter3

trait Printable[A] {
  self =>

  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] =
    new Printable[B] {
      def format(value: B): String =
        self.format(func(value))
    }
}

object Printable {

  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)

  def print[A](value: A)(implicit p: Printable[A]): Unit = println(format(value))

}

final case class Box[A](value: A)

object Box {
  implicit def boxPrinter[A](implicit aPrinter: Printable[A]): Printable[Box[A]] = aPrinter.contramap(_.value)
}