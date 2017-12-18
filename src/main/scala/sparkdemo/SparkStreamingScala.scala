package sparkdemo

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Created by DELL on 2017/11/23.
 */
object SparkStreamingScala {
    def main(args: Array[String]) {

//        val kafkaParams: Map[String, Object] = Map[String, Object](
//            "bootstrap.servers" -> "bourne:9092",
//            "key.deserializer" -> classOf[StringDeserializer],
//            "value.deserializer" -> classOf[StringDeserializer],
//            "group.id" -> "sparkstreamingtest",
//            "auto.offset.reset" -> "latest",
//            "enable.auto.commit" -> (false: java.lang.Boolean)
//        )

        val kafkaParams = Map[String, String]("metadata.broker.list" -> "bourne:2181")
        val topicsSet = Array("kafkatopic").toSet

        val conf = new SparkConf().setAppName("sparkstreaming").setMaster("local[1]").set("spark.driver.host","localhost")
        val ssc = new StreamingContext(conf, Seconds(2))

        val messages = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
                ssc, kafkaParams, topicsSet)

        val map: DStream[(String)] = messages.map(msg => msg._2)
        val words: DStream[String] = map.flatMap(line => line.split(" "))
        val wordCounts: DStream[(String, Int)] = words.map(x => (x, 1)).reduceByKey((a, b) => (a + b))

        wordCounts.print()
        ssc.start()
        ssc.awaitTermination()
    }
}
