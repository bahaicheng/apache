package sparkdemo

import java.util

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

//case class TcpObj(SrcIP: String, SrcPort: String, SrcMAC: String, DstIP: String, DstPort: String, DstMAC: String, TCPFlags: String, PayloadBytes: String)
case class TcpObj(SrcIP: String)

object SparkTcp {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().set("spark.driver.host", "localhost")
    val spark = SparkSession.builder().config(conf).appName("tcp").master("local").getOrCreate()
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

    list.foreach( o => println(o.SrcIP))


  }
}
