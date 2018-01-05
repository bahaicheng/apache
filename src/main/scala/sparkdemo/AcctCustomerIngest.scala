package sparkdemo


import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object AcctCustomerIngest {
  def main(args: Array[String]): Unit = {
    spark()
  }

  def spark(): Unit ={
    val spark = SparkSession.builder().appName("spark").master("local").config("spark.driver.host", "localhost").getOrCreate()
    val file = spark.read.json("D:\\data\\people.json")
    file.show()

  }
}
