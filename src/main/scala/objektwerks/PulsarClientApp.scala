package objektwerks

import com.sksamuel.pulsar4s._
import com.sksamuel.pulsar4s.circe._
import com.typesafe.config.ConfigFactory

import io.circe.generic.auto._

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object PulsarClientApp {
  def main(args: Array[String]): Unit = {
    val conf = ConfigFactory.load("app.conf")
    val url = conf.getString("url")
    val topicName = conf.getString("topicName")
    val subscriptionName = conf.getString("subscriptionName")

    val client = PulsarClient(url)
    val topic = Topic(topicName)
    val subscription = Subscription(subscriptionName)
    val config = ConsumerConfig(subscription, Seq(topic))

    val producer = client.producer[Event](ProducerConfig(topic))
    producer.sendAsync( Event(1, 1) )

    val consumer = client.consumer[Event](config)
    consumer.receive match {
      case Success(message) =>
        consumer.acknowledge(message.messageId)
        assert( message.value.key == message.value.value )
      case Failure(error) => println(error.getMessage())
    }

    producer.close()
    consumer.close()
  }
}