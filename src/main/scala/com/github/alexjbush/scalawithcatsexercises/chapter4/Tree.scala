package com.github.alexjbush.scalawithcatsexercises.chapter4

import cats.{Monad => CatsMonad}

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A])
  extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
    Branch(left, right)

  def leaf[A](value: A): Tree[A] =
    Leaf(value)

  implicit val treeMonad: CatsMonad[Tree] = new CatsMonad[Tree] {
    override def pure[A](a: A): Tree[A] = leaf(a)

    override def flatMap[A, B](fa: Tree[A])(f: A => Tree[B]): Tree[B] = fa match {
      case Leaf(a) => f(a)
      case Branch(l, r) => Branch(flatMap(l)(f), flatMap(r)(f))
    }

    override def tailRecM[A, B](a: A)(f: A => Tree[Either[A, B]]): Tree[B] = f(a) match {
      case Leaf(Left(_)) => tailRecM(a)(f)
      case Leaf(Right(b)) => pure(b)
      case Branch(l, r) =>

        def sub(t: Tree[Either[A, B]]): Tree[B] = flatMap(t) {
          case Left(sa) => tailRecM(sa)(f)
          case Right(b) => pure(b)
        }

        Branch(sub(l), sub(r))
    }

  }
}