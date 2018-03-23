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