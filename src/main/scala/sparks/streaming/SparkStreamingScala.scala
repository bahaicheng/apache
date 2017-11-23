package sparks.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}


/**
 * Created by DELL on 2017/11/23.
 */
object SparkStreamingScala {
    def main(args: Array[String]) {
        val conf = new SparkConf()
        val jssc = new StreamingContext(conf,Seconds(2))
    }
}
