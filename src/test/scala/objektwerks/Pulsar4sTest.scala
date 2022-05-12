package objektwerks

import com.sksamuel.pulsar4s._
import com.sksamuel.pulsar4s.circe._
import com.typesafe.config.ConfigFactory

import io.circe.generic.auto._

import org.scalatest.funsuite.AnyFunSuite

import scala.util.{Failure, Success}

case class Event(key: Int, value: Int)

class Pulsar4sTest extends AnyFunSuite {
  val conf = ConfigFactory.load("test.conf")
  val url = conf.getString("url")
  val topicName = conf.getString("topicName")
  val producerName = conf.getString("producerName")
  val subscriptionName = conf.getString("subscriptionName")

  test("produce > consume") {
    val client = PulsarClient(url)
    val topic = Topic(topicName)

    val producer = client.producer[Event](ProducerConfig(topic))
    producer.send( Event(1, 1) )

    val subscription = Subscription(subscriptionName)
    val consumerConfig = ConsumerConfig(subscription, Seq(topic))
    val consumer = client.consumer[Event](consumerConfig)
    consumer.receive match {
      case Success(message) => assert( message.value.key == message.value.value )
      case Failure(error) => fail(error.getMessage())
    }

    producer.close()
    consumer.close()
  }
}