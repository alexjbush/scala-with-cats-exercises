package com.github.alexjbush.scalawithcatsexercises.chapter4

import cats.data.State
import cats.data.State.{get, set}
import cats.syntax.applicative._

import scala.util.Try

object Calc {

  type CalcState[A] = State[List[Int], A]

  def unpackTwo(stack: List[Int]): (Int, Int, List[Int]) = stack match {
    case a :: b :: t => (a, b, t)
    case _ => throw new IllegalStateException(s"Not enough elements to unpack in stack ${stack.mkString(", ")}")
  }

  def operate(func: (Int, Int) => Int): CalcState[Int] = for {
    stack <- get[List[Int]]
    (a, b, smallStack) = unpackTwo(stack)
    r = func(b, a)
    _ <- set[List[Int]](r +: smallStack)
  } yield r

  def push(i: Int): CalcState[Int] = for {
    stack <- get[List[Int]]
    _ <- set[List[Int]](i :: stack)
  } yield i

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => operate(_ + _)
    case "*" => operate(_ * _)
    case "/" => operate(_ / _)
    case "-" => operate(_ - _)
    case i => Try(i.toInt).map(push).getOrElse(throw new IllegalArgumentException(s"Symbol $sym not supported"))
  }

  def evalAll(input: List[String]): CalcState[Int] = {

    input.foldLeft(0.pure[CalcState])((s, i) => s.flatMap(_ => evalOne(i)))
  }

  def evalInput(input: String): Int = {
    evalAll(input.split(" ").toList).runA(Nil).value
  }

}
