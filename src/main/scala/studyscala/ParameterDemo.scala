package studyscala

import entity.Parameter

object ParameterDemo {
  def main(args: Array[String]): Unit = {
    val p = new Parameter("bourne", 18, "man")
    println(p.name)
  }
}
