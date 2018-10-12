package com.github.alexjbush.scalawithcatsexercises.chapter1

trait Printable[A] {

  def format(value: A): String

}

object PrintableInstances {

  implicit val intPrinter: Printable[Int] = new Printable[Int] {
    override def format(value: Int): String = value.toString
  }

  implicit val stringPrinter: Printable[String] = new Printable[String] {
    override def format(value: String): String = value
  }

}

object Printable {

  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)

  def print[A](value: A)(implicit p: Printable[A]): Unit = println(format(value))

}


object PrintableSyntax {
  implicit class PrintableOps[A](value: A){

    def format(implicit p: Printable[A]): String = p.format(value)

    def print(implicit p: Printable[A]): Unit = println(p.format(value))

  }
}