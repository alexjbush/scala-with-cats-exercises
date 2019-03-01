package com.github.alexjbush.scalawithcatsexercises.chapter5

import cats.data.EitherT
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object Autobots {

  type Response[A] = EitherT[Future, String, A]

  val powerLevels: Map[String, Int] = Map(
    "Jazz" -> 6,
    "Bumblebee" -> 8,
    "Hot Rod" -> 10
  )

  def getPowerLevel(botName: String): Response[Int] = powerLevels.get(botName) match {
    case Some(l) => EitherT.right(Future.successful(l))
    case None => EitherT.left(Future.successful(s"$botName unreachable"))
  }

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] = for {
    ally1P <- getPowerLevel(ally1)
    ally2P <- getPowerLevel(ally2)
    canSM = (ally1P + ally2P) > 15
  } yield canSM

  def tacticalReport(ally1: String, ally2: String): String = Await.result(canSpecialMove(ally1, ally2).value, 1.second) match {
    case Left(e) => s"Comms error: $e"
    case Right(false) => s"$ally1 and $ally2 need a recharge."
    case Right(true) => s"$ally1 and $ally2 are ready to roll out!"
  }

}
