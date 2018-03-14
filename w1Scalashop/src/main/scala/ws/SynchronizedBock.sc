
class Account(private var amount: Int = 0) {
  private val x = new AnyRef
  private var uidCount = 0L

  def getUniqueID: Long = x.synchronized {
    uidCount = uidCount + 1
    uidCount
  }

  def transfer(target: Account, n: Int) =
    this.synchronized {
      target.synchronized {
        this.amount -= n
        target.amount += n
      }
    }

  private def lockAndTransfer(target: Account, n: Int) =
    this.synchronized {
      target.synchronized {
        this.amount -= n
        target.amount += n
      }
    }

  /* Resolving deadlocks*/
  val uid = getUniqueID
  def transgerNoDeadlock(target: Account, n: Int) =
    if (this.uid < target.uid) this.lockAndTransfer(target, n)
    else target.lockAndTransfer(this, -n)


}

/*Proxy method*/
def startThread(a: Account, b: Account, n: Int) = {
  val t = new Thread {
    override def run(): Unit = {
      for (i <- 0 until n) {
//        a.transfer(b, 1)
          a.transgerNoDeadlock(b,1)
      }
    }
  }
  t.start()
  t
}


val a1 = new Account(5000000)
val a2 = new Account(7000000)

/*Deadlock occurs*/
val t = startThread(a1, a2, 150000)
val s = startThread(a2, a1, 150000)

t.join()
s.join()



