

package object scalashop {

  /** The value of every pixel is represented as a 32 bit integer. */
  type RGBA = Int

  /** Returns the red component. */
  def red(c: RGBA): Int = (0xff000000 & c) >>> 24

  /** Returns the green component. */
  def green(c: RGBA): Int = (0x00ff0000 & c) >>> 16

  /** Returns the blue component. */
  def blue(c: RGBA): Int = (0x0000ff00 & c) >>> 8

  /** Returns the alpha component. */
  def alpha(c: RGBA): Int = (0x000000ff & c) >>> 0

  /** Used to create an RGBA value from separate components. */
  def rgba(r: Int, g: Int, b: Int, a: Int): RGBA = {
    (r << 24) | (g << 16) | (b << 8) | (a << 0)
  }

  /** Restricts the integer into the specified range. */
  def clamp(v: Int, min: Int, max: Int): Int = {
    if (v < min) min
    else if (v > max) max
    else v
  }

  /** Image is a two-dimensional matrix of pixel values. */
  class Img(val width: Int, val height: Int, private val data: Array[RGBA]) {
    def this(w: Int, h: Int) = this(w, h, new Array(w * h))
    def apply(x: Int, y: Int): RGBA = data(y * width + x)
    def update(x: Int, y: Int, c: RGBA): Unit = data(y * width + x) = c
  }

  /** Computes the blurred RGBA value of a single pixel of the input image. */
  def boxBlurKernel(src: Img, x: Int, y: Int, radius: Int): RGBA = {
    // TODO implement using while loops
    //    def avrg(c: => RGBA): Int = {}

    val minx: Int = x - radius
    val maxx: Int = x + radius

    val miny: Int = y - radius
    val maxy: Int = y + radius

    var r = 0
    var g = 0
    var b = 0
    var a = 0
//    println(s"minx = $minx and maxx = $maxx")
//    println(s"miny = $miny and maxy = $maxy")
    var curx: Int = clamp(0,minx,maxx)
    var cnt:Int = 0
    while (minx <= curx & curx <= maxx) {
      var cury: Int = clamp(0,miny,maxy)
      while (miny <= cury & cury <= maxy) {
        val c: RGBA = src.apply(curx, cury)
//        println(s"c = $c")
        r += red(c)
        g += green(c)
        b += blue(c)
        a += alpha(c)

        cury += 1
        cnt +=1
      }
      curx += 1
    }

    val ar = r / cnt
    val ag = g / cnt
    val ab = b / cnt
    val aa = a / cnt
//println(s"ar=$ar; ag=$ag; ab=$ab; aa=$aa;")
    rgba(ar, ag, ab, aa)
  }

}
