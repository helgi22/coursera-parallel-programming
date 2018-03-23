import reductions.LineOfSight.{Leaf, Node}
import sun.reflect.generics.tree.Tree

def reduce[A](t:Tree[A], f: (A,A) => A):A = t match {
  case Leaf(v) => v
  case Node(l,r) => {
    val (lv, rv) = parallel( reduce[A](l,f), reduce[A](r,f) )
    f(lv, rv)
  }
}



