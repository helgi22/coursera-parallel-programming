class HelloThread extends Thread {
  override def run(): Unit = {
    println("Hello Thread!")
  }
}

val t = new HelloThread

//Start thread
t.start()

//the main method wait  for HelloThread thread completion and then proceed himself running
t.join()

/*Demo*/
class HelloThread1 extends Thread {
  override def run(): Unit = {
    println("Hello")
    println("Thread!")
  }
}

def main(): Unit = {
  val t =new HelloThread1
  val s = new HelloThread1

  t.start()
  s.start()

  t.join()
  s.join()
}

main()
main()
main()
main()