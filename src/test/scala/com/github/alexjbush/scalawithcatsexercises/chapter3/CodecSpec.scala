package com.github.alexjbush.scalawithcatsexercises.chapter3

import org.scalatest.{FlatSpec, Matchers}

class CodecSpec extends FlatSpec with Matchers {


  it should "encode and decode integers, strings and doubles" in {

    Codec.encode(123) should be("123")

    Codec.decode[Int]("123") should be(123)

    Codec.encode(true) should be("true")

    Codec.decode[Boolean]("true") should be(true)

    Codec.encode(1.3d) should be("1.3")

    Codec.decode[Double]("1.3") should be(1.3d)

  }

  it should "encode and decode boxed doubles" in {

    Codec.encode(Box(1.3d)) should be ("1.3")

    Codec.decode[Box[Double]]("1.3") should be (Box(1.3d))

    Codec.encode(Box(Box(1.3d))) should be ("1.3")

    Codec.decode[Box[Box[Double]]]("1.3") should be (Box(Box(1.3d)))

  }


}
