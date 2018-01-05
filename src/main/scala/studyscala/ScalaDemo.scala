package studyscala

import java.io.{File, FileNotFoundException, FileReader, PrintWriter}
import scala.io.Source
import scala.util.matching.Regex

trait SuperTrait

case class caseClass1(name: String, age: Int) extends SuperTrait

case class caseClass2(name: String) extends SuperTrait

class Student(id: Int, name: String) {
  var age: Int = 0

  def showDetails() {
    println(id + " " + name + " " + age)
  }

  def this(id: Int, name: String, age: Int) {
    this(id, name) // Calling primary constructor, and it is first line
    this.age = age
  }
}

class ThreadExample extends Thread {
  override def run() {
    println("Thread is running")
  }
}

object ScalaDemo {
  def main(args: Array[String]): Unit = {
    //    str()
    //    array()
    //    list()
    //    map()
    //    option()
    //    println(matchTest(4))
    //    pattern()
    //    writeToLocal()
    //    consoleRead()
    //    readFromFile()
    //    caseClass()

    //    var s = new Student(1010,"Maxsu", 25);
    //    s.showDetails()

    //    var t = new ThreadExample()
    //    t.start()

    //    implicitTest()
//    vectordemo()
    seqdemo()
  }

  def seqdemo(): Unit ={
    var  seq = Seq((1,2,34),(-2,0,-3),("hello","world"))
    seq = seq.:+((1,2,3,4,5,6),("HM"))

    for (s <- seq){
      println(s)
    }
  }

  def vectordemo(): Unit ={
    var vet : Vector[Int] = Vector(1,2,3,4,5,6,7)
    println(vet)

    vet  = vet.:+(8)
    println(vet)

    vet  = vet.drop(2)
    println(vet)

    for (v <- vet){
        println("vetvet:"+v)
    }
  }


  def implicitTest(): Unit = {
    val demo = implicitDemo(1, 2)
    println("demo:" + demo)
  }

  implicit def implicitDemo(a: Int, b: Int): Int = a + b

  implicit def implicitDemo(a: String): Unit = println(a)

  def caseClass(): Unit = {
    callCase(caseClass1("lee", 1))
    callCase(caseClass2("bourne"))
  }

  def callCase(f: SuperTrait) = f match {
    case caseClass1(n, a) => println(n + "==" + a)
    case caseClass2(n) => println(n + "+++")
  }

  def readFromFile(): Unit = {
    val file = Source.fromFile("D://data/tcp.txt").foreach(x => print(x))
  }

  def consoleRead(): Unit = {
    print("Input your book:")
    val line = Console.readLine()
    println("my friend give me a book :" + line)
  }

  def writeToLocal(): Unit = {
    val write = new PrintWriter(new File("D://ScalaWriter"))
    write.write("Hello,World!!!")
    write.flush()
  }

  def exceptionDemo(): Unit = {
    try {
      val f = new FileReader("input.txt")
    } catch {
      case ex: FileNotFoundException => {
        println("Miss_File")
      }
    } finally {
      println("finish")
    }
  }

  def pattern(): Unit = {
    val pattern = "Scala".r
    val str = "Scala is scalable and cool"
    println(pattern findFirstIn (str))

    val pattern1 = new Regex("(S|s)cala")
    println((pattern1 findAllIn str).mkString(","))
  }

  def matchTest(num: Int): String = num match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }


  def option(): Unit = {
    var map: Map[String, String] = Map("red" -> "#FF0000", "azure" -> "#F0FFFF")
    map.get("red")
    map.get("aa")
    println(show(map.get("red")))
    println(show(map.get("aa")))

  }

  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }

  def map(): Unit = {
    var map: Map[String, String] = Map("red" -> "#FF0000", "azure" -> "#F0FFFF")
    map += ("aa" -> "bb")
    map -= ("red")
    map.foreach(m => println("key:" + m._1 + "==" + "value:" + m._2))
    println(map.get("aa"))

  }

  def set(): Unit = {
  }

  def list(): Unit = {
    val list: List[String] = List("apple", "oranges")
    val nums: List[Int] = List(1, 2, 3, 4, 5)
    val emptyList: List[Nothing] = List()
    val emptyList1 = Nil

    val newList = list:+"2"

    val fruit1 = "apples" :: ("oranges" :: ("pears" :: Nil))
    val fruit2 = "mangoes" :: ("banana" :: Nil)

    // use two or more lists with ::: operator
    var fruit = fruit1 ::: fruit2
    println("fruit1 ::: fruit2 : " + fruit)

    // use two lists with Set.:::() method
    fruit = fruit1.:::(fruit2)
    println("fruit1.:::(fruit2) : " + fruit)

    // pass two or more lists as arguments
    fruit = List.concat(fruit1, fruit2)
    println("List.concat(fruit1, fruit2) : " + fruit)

  }

  def array(): Unit = {
    var arr = new Array[String](3)
    arr(0) = "a"
    var arr1 = Array("aa", "bb", "cc")
    for (loop <- arr1) {
      println(loop)
    }
    for (i <- 1 to arr1.length - 1) {
      if (arr1(2).equals("cc")) println(arr1(2))
    }
  }

  def str(): Unit = {
    var floatVar = 12.456
    var intVar = 2000
    var stringVar = "Hello,Scala!"
    var fs = printf("%f,%d,%s", floatVar, intVar, stringVar)
    var name = "bourne"
    println(fs)
    println(s"hello,$name")
  }
}
