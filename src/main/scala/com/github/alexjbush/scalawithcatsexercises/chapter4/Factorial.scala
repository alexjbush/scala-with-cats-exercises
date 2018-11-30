package com.github.alexjbush.scalawithcatsexercises.chapter4

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._ // for pure

object Factorial {
  type Logged[A] = Writer[Vector[String], A]

  def slowly[A](body: => A): A =
    try body finally Thread.sleep(100)

  def factorial(n: Int): Logged[Int] = slowly {
    val ans = if (n == 0) 1.pure[Logged] else factorial(n - 1).map(_ * n)
    ans.tell(Vector(s"fact $n ${ans.value}"))
  }

}
