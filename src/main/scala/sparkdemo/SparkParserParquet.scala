package sparkdemo

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkParserParquet {
  def main(args: Array[String]): Unit = {
    spark()
  }

  def spark(): Unit ={
    val conf = new SparkConf().set("spark.driver.host", "localhost")
    val spark = SparkSession.builder().config(conf).appName("parquet").master("local").getOrCreate()
    val parquet = spark.read.parquet("D:\\data/users.parquet")
    parquet.printSchema()
    System.out.println("==============================")
    parquet.show()
    parquet.select("name","favorite_color").show()
  }
}
