package sparkdemo

import java.util
import java.util.Properties

import org.apache.kafka.common.serialization.StringSerializer
import org.apache.spark.SparkConf
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.SparkSession

//case class TcpObj(SrcIP: String, SrcPort: String, SrcMAC: String, DstIP: String, DstPort: String, DstMAC: String, TCPFlags: String, PayloadBytes: String)
case class TcpObj(SrcIP: String)

object SparkTcp {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().set("spark.driver.host", "localhost")
    val spark = SparkSession.builder().config(conf).appName("tcp").master("local").getOrCreate()

    val kafkaProducer: Broadcast[KafkaSink[String, String]] = {
      val kafkaProducerConfig = {
        val props = new Properties()
        props.put("bootstrap.servers", "bourne:9092")
        props.put("acks", "all")
        props.put("retries","0")
        props.put("batch.size", "16384")
        props.put("linger.ms", "1")
        props.put("buffer.memory", "33554432")
        props.setProperty("key.serializer", classOf[StringSerializer].getName)
        props.setProperty("value.serializer", classOf[StringSerializer].getName)
        props
      }
      spark.sparkContext.broadcast(KafkaSink[String, String](kafkaProducerConfig))
    }

    val lines = spark.read.textFile("D:\\data\\tcp.txt").rdd
    val obj = lines.map(line => {
      val spl = line.split(",")
//      new TcpObj(spl(0),spl(1),spl(2),spl(3),spl(4),spl(5),spl(6),spl(7))
      val asList = util.Arrays.asList(spl)
      asList
    })
    val list = obj.map(o => {
      val iterator = o.iterator()
      var obj =  new TcpObj("")
      while(iterator.hasNext){
        val next :Array[String] = iterator.next()
        obj = new TcpObj(next(0))
      }
      obj
    })

    list.foreach( o => {
      kafkaProducer.value.send("kafkatopic",o.SrcIP,o.toString)
    })
  }
}
