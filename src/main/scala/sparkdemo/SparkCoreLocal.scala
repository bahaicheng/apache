package sparkdemo

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel


object SparkCoreLocal {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().set("spark.driver.host", "localhost")
    val spark = SparkSession.builder().config(conf).appName("sparklocal").master("local").getOrCreate();
    val file = spark.read.textFile("D:\\data\\wc.txt").rdd
//    file.persist(StorageLevel.DISK_ONLY)
//    val map = file.flatMap(line => line.split(" "))

    val map = file.flatMap{
      line => line.split(" ")
    }

    val key = map.map(word => (word, 1)).reduceByKey(_ + _)
    key.foreach(word => println(word))
    key.sortByKey()
//    file.unpersist(blocking = true)

  }
}
