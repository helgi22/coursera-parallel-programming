/*
val inp = new Array[Int](10)

def reduceSeg[A](inp: Array[A], left: Int, right: Int, f: (A, A) => A): A = {
  if (right - left < threshhold) {
    var res = inp (left); var i = left+1
    while (i<right){ res = f(res,inp(i)); i = i+1}
    res
  }
  else {
    val mid= left+ (right- left)/2
    val (a1,a2) = parallel(reduceSeg(inp,left,mid,f),
      reduceSeg(inp, mid,right,f))
    f(a1,a2) //combine
  }
}

def reduce[A](int: Array[A], f: (A,A)=>A):A =
reduceSeg(int, 0, inp.length,f)

*/

def f(u: Double, v: Double): Double =
  (u + v)/(1.0 + u*v)

def err(lst:List[Double]): Double =
  lst.reduceLeft(f) - lst.reduceRight(f)

def testAssoc: Double = {
  val r = new scala.util.Random
  val lst = List.fill(400)(r.nextDouble*0.002)
  err(lst)
}

testAssoc
testAssoc
testAssoc
testAssoc

