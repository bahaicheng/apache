package sparkdemo

import kafka.serializer.StringDecoder
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

/**
  * Created by DELL on 2017/11/23.
  */
object SparkStreamingScala {
  def main(args: Array[String]) {
    createDirectStream()

  }

  private def createDirectStream(): Unit = {
    val kafkaParams: Map[String, Object] = Map[String, Object](
      "bootstrap.servers" -> "bourne.9092",
      "keydesrializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "sparkstreaming",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean))

    val topics: Array[String] = Array("kafkatopic", "topicA")

    val conf = new SparkConf().setAppName("sparkstreaming").setMaster("local")
    val ssc = new StreamingContext(conf, Seconds(2))
    val stream = KafkaUtils.createDirectStream(ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParams))

    val map = stream.map(msg => (msg.key(), msg.value()))

    ssc.start()
    ssc.awaitTermination()
  }
}
