package objektwerks

import com.sksamuel.pulsar4s.{DefaultProducerMessage, EventTime, ProducerConfig, PulsarClient, Topic}
import com.sksamuel.pulsar4s.circe._
import com.typesafe.config.ConfigFactory

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

import io.circe.generic.auto._

class Pulsar4sTest extends AnyFunSuite with Matchers {
  val conf = ConfigFactory.load("test.conf")
  val url = conf.getString("url")
  val topicName = conf.getString("topicName")
  val producerName = conf.getString("producerName")

  val client = PulsarClient(url)
  val topic = Topic(topicName)

  val producer = client.producer[SensorEvent](ProducerConfig(
    topic, 
    producerName = Some(producerName),
    enableBatching = Some(true),
    blockIfQueueFull = Some(true))
  )



  test("produce > consume") {

    (0 until 100) foreach { _ =>
      val sensorEvent = SensorDomain.generate()
      val message = DefaultProducerMessage(
        Some(sensorEvent.sensorId), 
        sensorEvent, 
        eventTime = Some(EventTime(sensorEvent.eventTime)))
      
      producer.sendAsync(message) 
    }
  }
}