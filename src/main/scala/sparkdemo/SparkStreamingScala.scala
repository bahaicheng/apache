package sparkdemo

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf

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

//    val conf = new SparkConf().setAppName("sparkstreaming").setMaster("local")
//    val ssc = new StreamingContext(conf, Seconds(2))
//    val stream = KafkaUtils.createDirectStream(ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParams))
//    ssc.checkpoint("")
//    val union = stream.union(stream)
//    val map = union.map(msg => (msg.key(), 1))
//
//    val window = map.reduceByKeyAndWindow((a:Int,b:Int) => (a+b),Seconds(5),Seconds(5))
//
//    val mergeValue = map.updateStateByKey((values:Seq[Int],state:Option[Int]) => {
//      val currentCount = values.sum
//      val previousCount = state.getOrElse(0)
//      Some(currentCount + previousCount)
//    })
//    mergeValue.print()
//    ssc.start()
//    ssc.awaitTermination()
  }
}
