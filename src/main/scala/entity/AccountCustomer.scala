package entity

case class AccountCustomer(accountNo: String, customerNo: String) {
  override def toString: String = accountNo + "," + customerNo
}