package sparkdemo

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent

object Streaming {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().set("spark.driver.host", "localhost")
    val spark = SparkSession.builder().config(conf).master("local").appName("sparkdemo").getOrCreate()
    val sc = spark.sparkContext
    val ssc = new StreamingContext(sc,Seconds(1))

    val kafkaParams: Map[String, Object] = Map[String, Object](
      "bootstrap.servers" -> "bourne.9092",
      "keydesrializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "sparkstreaming",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean))
    val topics: Array[String] = Array("kafkatopic", "topicA")
    val stream = KafkaUtils.createDirectStream(ssc,PreferConsistent,Subscribe[String, String](topics, kafkaParams))
    ssc.checkpoint("")
    val union = stream.union(stream)




  }
}
