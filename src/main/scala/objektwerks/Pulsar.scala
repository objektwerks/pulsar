package objektwerks

import com.sksamuel.pulsar4s._

object Pulsar {
  def close(producer: Producer[Event], consumer: Consumer[Event]): Unit = {
    producer.close()
    consumer.close()
  }
}