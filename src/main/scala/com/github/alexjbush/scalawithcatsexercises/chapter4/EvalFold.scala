package com.github.alexjbush.scalawithcatsexercises.chapter4

import cats.Eval


object EvalFold {

  def foldRightStackUnsafe[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    as match {
      case head :: tail =>
        fn(head, foldRightStackUnsafe(tail, acc)(fn))
      case Nil =>
        acc
    }

  def foldRightStackSafe[A, B](as: List[A], acc: B)(fn: (A, B) => B): Eval[B] =
    as match {
      case head :: tail =>
        for {
          t <- Eval.defer(foldRightStackSafe(tail, acc)(fn))
          r <- Eval.later(fn(head, t))
        } yield r
      case Nil =>
        Eval.now(acc)
    }

  def foldRightEval[A, B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer(fn(head, foldRightEval(tail, acc)(fn)))
      case Nil =>
        acc
    }

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = {
    foldRightEval(as, Eval.now(acc))((a, b) => b.map(fn(a,_))).value
  }

}
