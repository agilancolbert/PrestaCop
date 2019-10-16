import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.SparkConf
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._



object stream {


def main(args: Array[String]): Unit = {

  val sparkConf = new SparkConf().setAppName("MyStream").setMaster("local[*]")

  val ssc = new StreamingContext(sparkConf, Seconds(1))

  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> "localhost:9092,localhost:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "group_1",
    "auto.offset.reset" -> "latest",
    "zookeeper.consumer.connection" -> "localhost:2181",
    "kafka-consumer-id" -> "kafka-consumer",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

  val topics = Array("quick-start")
  val stream = KafkaUtils.createDirectStream[String, String](
    ssc,
    PreferConsistent,
    Subscribe[String, String](topics, kafkaParams)
  )
   //stream.saveAsTextFile("save")

  /*stream.map(record => (record.key, record.value))
  print(stream.map(record => (record.key, record.value)))*/
  stream.foreachRDD(rdd => {
	//println("number of records : " + rdd.count())
 	val data = rdd.collect().mkString("\n")
	println(data)
})

   
  ssc.start()
  ssc.awaitTermination()
 }
}
