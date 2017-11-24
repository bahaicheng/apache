package sparkdemo

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Created by DELL on 2017/11/23.
 */
object SparkStreamingScala {
    def main(args: Array[String]) {

        val kafkaParams: Map[String, Object] = Map[String, Object](
            "bootstrap.servers" -> "bourne:9092",
            "key.deserializer" -> classOf[StringDeserializer],
            "value.deserializer" -> classOf[StringDeserializer],
            "group.id" -> "sparkstreamingtest",
            "auto.offset.reset" -> "latest",
            "enable.auto.commit" -> (false: java.lang.Boolean)
        )

        val topics = Array("kafkatopic").toSet

        val conf = new SparkConf().setAppName("sparkstreaming").setMaster("local[1]")
        val ssc = new StreamingContext(conf, Seconds(2))
        val messages = KafkaUtils.createDirectStream[String, String](ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParams))

        val map: DStream[(String)] = messages.map(msg => msg.value())
        val words: DStream[String] = map.flatMap(line => line.split(" "))

        val wordCounts: DStream[(String, Int)] = words.map(x => (x, 1)).reduceByKey((a, b) => (a + b))

        wordCounts.print()

        ssc.start()
        ssc.awaitTermination()
    }
}
