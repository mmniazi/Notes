// Water pouring problem
// Suppose you have been given n glasses each with specific
// capacity. You can:
// 1 - completely fill the glass
// 2 - empty it
// 3 - Pour it to another glass
// So create a program that fills a glass to certain capacity

class Pouring(capacity: List[Int]) {
  type State = List[Int]
  val initState: State = capacity map (_ => 0)
  val glasses = initState.indices

  trait Move {
    def change(state: State): State
  }

  case class Fill(glass: Int) extends Move {
    def change(state: State) = state updated(glass, capacity(glass))
  }

  case class Empty(glass: Int) extends Move {
    def change(state: State) = state updated(glass, 0)
  }

  case class Pour(from: Int, to: Int) extends Move {
    def change(state: State) = {
      val amount = state(from) min (capacity(to) - state(to))
      state updated(from, state(from) - amount) updated(to, state(to) + amount)
    }
  }

  val moves = (for (g <- glasses) yield Empty(g)) ++
    (for (g <- glasses) yield Fill(g)) ++
    (for (gf <- glasses; gt <- glasses; if gf != gt) yield Pour(gf, gt))

  class Path(history: List[Move], val endState: State) {
    def extend(move: Move) = new Path(move :: history, move change endState)

    override def toString = (history.reverse mkString " ") + "-->" + endState
  }

  val initPath = new Path(Nil, initState)

  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if (paths.isEmpty) Stream.empty
    else {
      val more = for {
        path <- paths
        next <- moves map path.extend
        if !(explored contains next.endState)
      } yield next
      paths #:: from(more, explored ++ (more map (_.endState)))
    }

  val pathSets = from(Set(initPath), Set(initState))

  def solution(target: Int): Stream[Path] = for {
    pathSet <- pathSets
    path <- pathSet
    if path.endState contains target
  } yield path
}

val problem = new Pouring(List(4, 3))
problem.solution(2)