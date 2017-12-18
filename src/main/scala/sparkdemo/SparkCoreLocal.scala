package sparkdemo

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession


object SparkCoreLocal {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().set("spark.driver.host", "localhost")
    val spark = SparkSession.builder().config(conf).appName("sparklocal").master("local").getOrCreate();
    val file = spark.read.textFile("").rdd

    val map = file.flatMap(line => line.split(" "))
    val key = map.map(word => (word,1)).reduceByKey(_+_)

  }
}
