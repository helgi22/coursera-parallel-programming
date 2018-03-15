
object test {
  import org.scalameter._

  val time = measurer {
    (0 until 100000).toArray
  }
  println(s"Array initializing times: $time ms")
}


test
