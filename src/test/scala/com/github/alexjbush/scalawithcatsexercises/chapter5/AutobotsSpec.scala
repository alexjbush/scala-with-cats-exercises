package com.github.alexjbush.scalawithcatsexercises.chapter5

import org.scalatest.{FlatSpec, Matchers}
import Autobots.tacticalReport
class AutobotsSpec extends FlatSpec with Matchers {

  it should "Follow the example" in {

    tacticalReport("Jazz", "Bumblebee") should be (
      "Jazz and Bumblebee need a recharge."
    )

    tacticalReport("Bumblebee", "Hot Rod") should be (
      "Bumblebee and Hot Rod are ready to roll out!"
    )

    tacticalReport("Jazz", "Ironhide") should be (
      "Comms error: Ironhide unreachable"
    )

  }

}
