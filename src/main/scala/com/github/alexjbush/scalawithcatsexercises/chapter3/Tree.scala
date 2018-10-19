package com.github.alexjbush.scalawithcatsexercises.chapter3

import cats.Functor
import cats.implicits._

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A])
  extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {
  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case b: Branch[A] => b.map(f)
      case l: Leaf[A] => l.map(f)
    }
  }
}

object Leaf {

  implicit val leafFunctor: Functor[Leaf] = new Functor[Leaf] {
    override def map[A, B](fa: Leaf[A])(f: A => B): Leaf[B] = Leaf(f(fa.value))
  }

}

object Branch {

  implicit val branchFunctor: Functor[Branch] = new Functor[Branch] {
    override def map[A, B](fa: Branch[A])(f: A => B): Branch[B] = Branch(fa.left.map(f), fa.right.map(f))
  }

}