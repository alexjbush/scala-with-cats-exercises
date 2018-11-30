package com.github.alexjbush.scalawithcatsexercises.chapter4

import org.scalatest.{FlatSpec, Matchers}

class DbSpec extends FlatSpec with Matchers{

  val testDb: Db = Db(Map(0 -> "test user"), Map("test user" -> "test password"))

  it should "Check if a username exists" in {
    Db.findUsername(0).run(testDb) shouldBe Some("test user")
    Db.findUsername(1).run(testDb) shouldBe None
  }

  it should "Check if a user's password is correct" in {
    Db.checkPassword("test user", "test password").run(testDb) shouldBe true
    Db.checkPassword("test user", "wrong password").run(testDb) shouldBe false
    Db.checkPassword("wrong user", "test password").run(testDb) shouldBe false
  }

  it should "Check a user's login details" in {
    Db.checkLogin(0, "test password").run(testDb) shouldBe true
    Db.checkLogin(0, "wrong password").run(testDb) shouldBe false
    Db.checkLogin(1, "test password").run(testDb) shouldBe false
  }

}
