def mapASegSec[A,B](inp: Array[A], left: Int, rigth: Int, f: A => B, out: Array[B]) = {
//  Writes to out(i) for left <= i <= right -1
  var i = left
  while (i < rigth) {
    out(i) = f(inp(i))
    i = i+1
  }
}
val in = Array(2,3,4,5,6)
val out = Array(0,0,0,0,0)
val f = (x:Int) => x*x
mapASegSec(in, 1,3,f,out)
out

def mapASegPar[A, B](inp: Array[A], left: Int, right: Int, f: A => B, out: Array[B]) = {
  import common.parallel
  //writes to out(i) for left <= i <= right -1
  if (right - left < threshold) //threshold must be several order of magnitude large
    mapASegSec(inp, left, right, f, out)
  else {
    val mid = left + (right - left) / 2
    parallel(mapASegPar(inp, left, mid, f, out),
      mapASegPar(inp, mid, right, f, out))
  }
}


val p: Double =1.5

def f(x:Int):Double = power(x,p)

mapASegSec(inp,  0 , inp.length, f, out) //sequential
mapASegPar(inp,  0 , inp.length, f, out) //parallel

