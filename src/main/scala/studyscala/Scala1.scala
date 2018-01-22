package studyscala



object Scala1 {
  implicit def str2int(s: String) = s.toInt

  def main(args: Array[String]): Unit = {
    val a  =  math.max("2",1)
    println(a)
    other()
  }

  def other(): Unit ={
    val a  =  math.max("3",5)
    println(a)
  }



}




