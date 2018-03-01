package sparkdemo

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object SparkSortBy {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().set("spark.driver.host", "localhost")
    val spark = SparkSession.builder().config(conf).appName("sortby").master("local").getOrCreate()
    val arr = Array((1, 6, 3), (2, 3, 3), (1, 1, 2), (1, 3, 5), (2, 1, 2))
    val parallelize = spark.sparkContext.parallelize(arr)
    parallelize.foreach(println)
    val rdd2 = parallelize.map(e => ((e._1, e._3), e))
    // 利用sortByKey排序的对key的特性
    val rdd3 = rdd2.sortByKey(true, 6)
    //    val rdd4 = rdd3.values.collect
    rdd3.saveAsTextFile("D:/data/sortby")
  }
}
