package sparkdemo

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkParserCsv {
  def main(args: Array[String]): Unit = {
    spark()
  }

  def spark(): Unit ={
    val conf = new SparkConf().set("spark.driver.host", "localhost")
    val spark = SparkSession.builder().config(conf).appName("parquet").master("local").getOrCreate()
    val parquet = spark.read.csv("D:\\data/2000_cols_data.csv")
    parquet.printSchema()
    parquet.show()
    parquet.select("_c0").show()
    parquet.select()
  }
}
