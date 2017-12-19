package sparkdemo

import entity.AccountCustomer
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object AccountCustomerIngest {
  def main(args: Array[String]): Unit = {
    val path = "D:\\data/tcp.txt"
    spark(path)
  }

  def spark(path: String): Unit = {
    val conf = new SparkConf().setAppName("acct").setMaster("local").set("spark.driver.host", "localhost")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val lines = spark.read.textFile(path).rdd

    lines.foreach(map => println("lines:" + map))

    val map = lines.map(line => {
      val spl = line.split(",")
      (spl(0), spl(1))
    }
    )
    map.foreach(map => println("map" + map))
    val mapObj = map.map(tuple => {
      new AccountCustomer(tuple._2, tuple._1)
    })
    mapObj.foreach(obj => println(obj.accountNo))
  }
}
