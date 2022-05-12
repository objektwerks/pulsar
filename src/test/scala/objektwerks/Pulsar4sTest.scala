package objektwerks

import com.typesafe.config.ConfigFactory

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class Pulsar4sTest extends AnyFunSuite with Matchers {
  val conf = ConfigFactory.load("test.conf")
  val url = conf.getString("url")

  test("produce > consume") {

  }
}