package objektwerks

import com.sksamuel.pulsar4s._
import com.sksamuel.pulsar4s.circe._
import com.typesafe.config.ConfigFactory

import io.circe.generic.auto._

import org.scalatest.funsuite.AnyFunSuite

import scala.util.{Failure, Success}

class PulsarClientTest extends AnyFunSuite {
  val conf = ConfigFactory.load("test.conf")
  val url = conf.getString("url")
  val topicName = conf.getString("topicName")
  val subscriptionName = conf.getString("subscriptionName")

  test("produce > consume") {
    val client = PulsarClient(url)
    val topic = Topic(topicName)
    val subscription = Subscription(subscriptionName)
    val config = ConsumerConfig(subscription, Seq(topic))

    val producer = client.producer[Event](ProducerConfig(topic))
    producer.send( Event(1, 1) )

    val consumer = client.consumer[Event](config)
    consumer.receive match {
      case Success(message) => assert( message.value.key == message.value.value )
      case Failure(error) => fail(error.getMessage())
    }

    producer.close()
    consumer.close()
  }
}