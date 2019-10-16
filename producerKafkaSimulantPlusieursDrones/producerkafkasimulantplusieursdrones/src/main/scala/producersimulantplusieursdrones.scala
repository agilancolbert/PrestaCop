import java.util.Properties

import org.apache.kafka.clients.producer._

object producersimulantplusieursdrones {

  def main(args: Array[String]): Unit = {

    writeToKafka("quick-start")

  }

  def writeToKafka(topic: String): Unit = {

    val props = new Properties()

    props.put("bootstrap.servers", "localhost:9092")

    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    //csv path to be changed
    val bufferedSource = scala.io.Source.fromFile("/home/osboxes/Desktop/MyCSV.csv")
    val producer = new KafkaProducer[String, String](props)
    bufferedSource.getLines.drop(1).foreach { line =>
    var cols = line.split(",").map(_.trim)
    var message = "{ " + "summonsNumber : " + "\"" + s"${cols(0)}" + "\""  + ", plateId : " + "\"" + s"${cols(1)}" + "\"" +
		         ", state : "+ "\"" + s"${cols(2)}"+ "\"" + ", date : "+ "\"" + s"${cols(3)}"+ "\"" +
       			 ", violationCode : "+ "\"" + s"${cols(4)}" + "\"" + ", streetCode : "+ "\"" + s"${cols(5)}" + "\"" +
			 ", houseNo. : " + "\"" + s"${cols(6)}"+ "\"" + ", streetname : " + "\"" + s"${cols(7)}"+ "\"" + 
	         " }"
    val record = new ProducerRecord[String, String](topic, "key", message)
    producer.send(record)
    }
    bufferedSource.close   
    producer.close()

    println("\nj ai finis d'envoyer")
  }

}
