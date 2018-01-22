package studyscala

class Account (val id: Int, init : Double) {
  var balance = init
}

object Account {
  private var lastNumber = 0
  private def newUniqueNumber() ={lastNumber+=1;lastNumber}

  def apply(init : Double) ={
      new Account(newUniqueNumber(),init)
  }
}
