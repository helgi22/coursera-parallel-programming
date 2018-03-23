import reductions.LineOfSight.{Leaf, Node}
import sun.reflect.generics.tree.Tree

def mapTreePar[A: Manifest, B: Manifest](t: Tree[A], f: A => B): Tree[B] =
  t match {
    case Leaf(a) => {
      val len = a.lenght;
      val b = new Array[B](len)
      var i = 0
      while (i < len) {
        b(i) = f(a(i)); i = i + 1
      }
      Leaf(b)
    }
    case Node(l,r) => {
      val (lb,rb) = parallel(mapTreePar(1,l), mapTreePar(r,f))
      Node(lb,rb)
    }
  }