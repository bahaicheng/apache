package sparkdemo


import org.apache.spark.sql.SparkSession

object AcctCustomerIngest {
  def main(args: Array[String]): Unit = {
    spark()
  }

  def spark(): Unit ={
    val spark = SparkSession.builder().appName("spark").master("local").config("spark.driver.host", "localhost").getOrCreate()
    val file = spark.read.textFile("").rdd
    val parser = file.map( line => {
      var tuple = new Tuple2[String,String]("","")
      val spl = line.split(",")
      if(spl.length < 2 || spl(0).isEmpty || spl(1).isEmpty){
        tuple = new Tuple2[String,String]("NA",line)
      }else{
        tuple = new Tuple2[String,String](spl(0), spl(1))
      }
      tuple
    })
    val filterMsg = parser.filter( t => {
      var flag = true
      if(t._1 ==  "NA"){
            flag = false
      }
      flag
    })

  }
}
