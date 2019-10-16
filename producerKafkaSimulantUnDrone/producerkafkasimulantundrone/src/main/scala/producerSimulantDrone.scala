import java.util.Properties

import org.apache.kafka.clients.producer._

object ProducerSimulantDrone {

  def main(args: Array[String]): Unit = {

    writeToKafka("quick-start")

  }

  def writeToKafka(topic: String): Unit = {

    val props = new Properties()

    props.put("bootstrap.servers", "localhost:9092")

    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)
    var message = "{\n summonsNumber : \"1234\",\n plateId : \"AB 024 CD\",\n state : \"NY\",\n date : \"14/10/2019\",\n" + 		         "violationCode : \"412\",\n streetCode : \"21\",\n houseNo. : \"1\",\n streetname : \"Peace Street\"\n }"
    val record = new ProducerRecord[String, String](topic, "key", message)
    producer.send(record)
    producer.close()

    println("\nj ai finis d'envoyer")
  }

}
