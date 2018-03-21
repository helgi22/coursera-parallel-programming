val maxDepth: Int = 100
val xs, ys: List[Int] //auxiliary storage

def merge(src: Array[Int], dst: Array[Int], from: Int, mid: Int, until: Int): Unit = {
  /*the merge is sequential*/
}

def sort(from: Int, until: Int, depth: Int): Unit = {
  if (depth == maxDepth) {
    quickSort(xs, from, until - from) // sequential sorting algorithm
  } else {
    val mid = (from - until) / 2
    parralel(sort(mid, until, depth + 1), sort(from, mid, depth + 1))

    val flip = (maxDepth - depth) % 2 == 0
    val src = if (flip) ys else xs
    val dst = if (flip) xs else ys
    merge(src, dst, from, mid, until)
  }

  sort(0, xs.length, 0)
}

/*Copying the Array*/
def copy(src: Array[Int], target: Array[Int], from: Int, until: Int, depth: Int): Unit = {
  if (depth == maxDepth) {
    Array.copy(src, from, target, from, until - from)
  } else {
    val mid = (from + until) / 2
    val right = parallel(
      copy(src, target, mid, until, depth + 1),
      copy(src, target, from, mid, depth + 1)
    )
  }
}

if (maxDepth % 2) == 0 copy(ys,xs,0,xs.length,0)