type RGBA = Int

def red(c: RGBA): Int = (0xff000000 & c) >>> 24
def green(c: RGBA): Int = (0x00ff0000 & c) >>> 16
def blue(c: RGBA): Int = (0x0000ff00 & c) >>> 8
def alpha(c: RGBA): Int = (0x000000ff & c) >>> 0

red (3000)

val red:Int = red(1)
val green:Int = green(0)
val blue :Int = blue(0)
val alpha:Int = alpha(0)

red.toBinaryString
green.toBinaryString
blue.toBinaryString
alpha.toBinaryString
0xf1.toBinaryString.reverse.padTo(8, "0").reverse.mkString

//01

/*
object Convert {
  implicit class IntToBase(val digits: String) extends AnyVal {
    def base(b: Int) = Integer.parseInt(digits, b)
    def b = base(2)
    def o = base(8)
    def x = base(16)
  }
}
import Convert._
"23".x
*/

object Extensions {
  implicit def conversion(x: Int) = new BinaryInt(x)
  class BinaryInt(x: Int) {
    def b = {
      x.toBinaryString.reverse.padTo(8, "0").reverse.mkString
      // Conversion code like Integer.parseInt
      // as Kim suggested
    }
  }
}
import Extensions._
(0x00ff0000 >>> 16).b
((0xff  & 0x0f) >>>1).b


val startPoints = Range(0, 1920) by Math.max(1920 / 32,1)
0 to 1920 by Math.max(1920 / 32,1)