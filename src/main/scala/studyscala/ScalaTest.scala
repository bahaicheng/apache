package studyscala


object ScalaTest {
  def main(args: Array[String]): Unit = {
    var list: List[String] = List("aa")
    val str: Array[String] = "bb,cc,dd".split(",")
    for (l <- str) {
      list = list.:+(l)
    }
    println(list)
  }
}
