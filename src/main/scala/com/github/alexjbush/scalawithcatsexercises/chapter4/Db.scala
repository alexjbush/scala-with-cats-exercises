package com.github.alexjbush.scalawithcatsexercises.chapter4

import cats.data.Reader
import cats.syntax.applicative._ // for pure
case class Db(usernames: Map[Int, String], passwords: Map[String, String])

object Db {

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] = Reader(_.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] = Reader(_.passwords.get(username).contains(password))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] = {
    for {
      u <- findUsername(userId)
      p <- u.map(checkPassword(_, password)).getOrElse(false.pure[DbReader])
    } yield p
  }
}