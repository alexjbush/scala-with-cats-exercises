package com.github.alexjbush.scalawithcatsexercises.chapter4

object Id {

  type Id[A] = A

  val idMonad: Monad[Id] = new Monad[Id] {
    override def pure[A](a: A): Id[A] = a

    override def flatMap[A, B](value: Id[A])(func: A => Id[B]): Id[B] = func(value)
  }

}