package utils

import java.io.{File, PrintWriter}

import scala.io.Source


object ScalaReadAndWrite {
  def main(args: Array[String]): Unit = {
    //    read()
    //    write()
  }

  def read(): Unit = {
    val file = Source.fromFile("D:/data/tcp.txt")
    for (line <- file.getLines()) {
      println(line)
    }
  }

  def write(): Unit = {
    val write = new PrintWriter(new File("D:/data/scalaWrite"))
    write.print("hao")
    write.close()
  }
}