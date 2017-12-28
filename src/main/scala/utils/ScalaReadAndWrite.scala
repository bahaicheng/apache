package utils

import java.io.{File, PrintWriter}

import scala.io.Source


object ScalaReadAndWrite {
  def main(args: Array[String]): Unit = {
    //    read()
    write()
  }

  def read(): Unit = {
    val file = Source.fromFile("D:/data/tcp.txt")
    for (line <- file.getLines()) {
      println(line)
    }
    val url = Source.fromURL("http://www.baidu.com")
    url.foreach(print _)
  }

  def write(): Unit = {
    val write = new PrintWriter(new File("D:/data/scalaWrite"))
    for (a <- 1 to 100000000) {
      write.print("hadoop hive hive kafka kafka hbase hue hue spark flume flume oozie oozie sqoop sqoop" + "\r\n")
    }
    write.close()
  }
}