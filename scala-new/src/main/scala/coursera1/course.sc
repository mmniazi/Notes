// Take left most operator and evaluate operands and apply
// operator operands.
// Parameters with a definition:
def square(x: Double) = x * x
// Function evaluation:
// Functions can be evaluated in two ways
// 1 - Call by value:
// evaluate the parameters before passing into function i.e
// in def square(2, 3+5) 3+5 will be converted to 8 and then
// passed in to the function.
// 2 - Call by Name:
// lazily evaluate the parameters when they are needed

// Scala uses call by value by default and you can call by name
// using => e.g def fnt (x:=> Int) = 1
// Conditional expressions:
def abs(x: Int) = if (x >= 0) x else -x
// We can have to type of definitions
// 1 - Definition by name: "using def keyword" = lazy
// 2 - Definition by value: "using val keyword"

// Blocks can be used to avoid namespace pollution by encapsulating
// all function required by a function inside a block and then
// returning the value of function as last expression of block
// Block are expressions so they can be used where expressions can
// be used. Blocks are delimited by {}
// Example of Blocks:
def cube(x: Int): Int = {
  def square(x: Int) = x * x
  x * square(x)
}

// Semicolons:
// Either end the line with operator or encapsulate expressions
// with ()
// 1
// + 2 --> Error: as the 1 is a separate statement and + 2 is separate
// Either write
// 1 +
// 2
// Or
// (1
// + 2)

// Tail recursion:
// To understand it look at these two functions
def factorial(n: Int): Int =
  if (n == 0) 1 else n * factorial(n - 1)
// Note that factorial is not only calling itself as a last
// statement instead its calling something that need further
// computation to find a final value. So stack cannot be reused.
// Now look at this function:
def gcd(a: Int, b: Int): Int =
  if (b == 0) a else gcd(b, a % b)
// As its only calling itself and there is nothing remaining to be
// done on this stack so the stack can be reused. These call are
// called tail calls.
// As above definition of factorial is not tail recursive so lets
// make it tail recursive
def factorial_tr(n: Int, rem: Int): Int =
  if (n == 0) rem else factorial_tr(n - 1, rem * n)
if (factorial(5) == factorial_tr(5, 1)) println("success")
// Higher order functions:
// we can pass functions as values to functions and
// return them from functions, such functions are
// called higher order functions
// TYPE => TYPE is used to define higher order functions
def secOrderFun(myFun: Int => Double, a: Int): Double =
  myFun(a)
// We can also have anonymous functions
(x: Int) => x * x
// As a side note return type of function can be omitted
// in all cases except of recursion as compiler can infer
// type automatically

// Currying:
// Instead of defining function as
// def product(f: Int => Int, a: Int, b: Int)
// we can write the function as special syntax like
// below so that we can use function without passing
// any parameters when needed
def product(f: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) 1 else f(a) * product(f)(a + 1, b)
}
product(x => x * x)(3, 4)
// Writing factorial in term of product
def factorial(a: Int, b: Int) = product(x => x)(a, b)
factorial(1, 4)
// Writing the product, sum etc function in term of
// general function
def mapReduce(f: (Int, Int) => Int, initialVal: Int)
             (g: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) initialVal
  else f(g(a), product(g)(a + 1, b))
}
// Product in term of mapReduce
mapReduce((x, y) => x * y, 1)(x => x)(1, 4)
// Data:
// defining classes for creating data types
// Functions inside classes are called methods
class Rational(x: Int, y: Int) {
  // To guard against user creating a rational with
  // denominator as zero we can use require, a built in
  // function that makes sure that upon construction of
  // this class certain criteria are met.
  // If require fail an IllegalArgumentException is
  // thrown
  require(y != 0, "Denominator must be non-zero")

  // The default constructor of scala takes all parameters
  // passed to class and executes complete class body
  // But we can have multiple constructors using "this"
  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  val num = x / gcd(x, y)

  val denom = y / gcd(x, y)

  // We can have symbols as names in scala

  def <(that: Rational) =
    this.num * that.denom < this.denom * that.num

  def max(that: Rational) =
    if (this < that) this else that

  // To have function name before the object we have
  // to name it as unary_functionName, so we can write
  // -this instead of this.-
  def unary_-() = new Rational(-num, denom)

  def +(that: Rational) =
    new Rational(num * that.denom + denom * that.num,
      denom * that.denom)

  def -(that: Rational) = this + (-that)

  override def toString = num + "/" + denom
}

val x = new Rational(3, 2)
x.denom
val y = new Rational(1, 1)
val z = new Rational(4, 3)
println(y - x - z)
x < y
y max z
//new Rational(1, 0)
// Beside require there is assert which throws
// AssertionError. Both shows different intents of coder
// Assert is used to check code of function itself
// Require is used to enforce a pre-condition on the
// caller of a function
assert(y < x)
// We can call alternative constructors
new Rational(3)
// Abstract class
abstract class Inset {
  def incl(x: Int): Inset

  def contains(x: Int): Boolean
}

// As all empties are same so we can represent them
// using a single instance of class by creating a
// singleton object
object Empty extends Inset {
  override def incl(x: Int): Inset =
    new NonEmpty(x, Empty, Empty)

  override def contains(x: Int): Boolean = false
}

class NonEmpty(elem: Int, left: Inset, right: Inset)
  extends Inset {
  override def incl(x: Int): Inset =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  override def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
}

// Check sample.SampleMain

// In scala as well as java we can only have one super
// class but in scala we can implement multiple traits
// the difference between scala trait and java interface
// is that traits can have fields and methods implementation
// but they cant have value parameters
trait Planar {
  def height: Int

  def width = 19

  def surface = height * width
}
class Shape {}

class Square(x: Int, y: Int) extends Shape with Planar {
  override def height: Int = 5
}
// Throwing Exception:
def myError(msg: String) = throw new Error(msg)

//myError("some random error")
// if we have val in class parameters then fields are
// automatically created and initialized using parameters
class Vals(val x: Int, val y: Int) {}
// Type parameterization
trait List2[T] {
  def isEmpty: Boolean

  def head: T

  def tail: List2[T]
}

class Cons[T](val head: T, val tail: List2[T])
  extends List2[T] {
  def isEmpty = false
}

class Nil[T] extends List2[T] {
  def isEmpty = true

  def head: Nothing = throw new NoSuchElementException("Nil.head")

  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}
// We can also have generic type in functions
def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
singleton(1)
// Polymorphism i.e using multiply types can be done in two ways
// 1 - Generics : associated more with functional prog
// 2 - Sub-typing: associated more with oop prog
def nth[T](n: Int, list: List2[T]) {
  if (list.isEmpty) throw new IndexOutOfBoundsException
  else if (n == 0) list.head
  else nth(n - 1, list.tail)
}
val list = new Cons(1, new Cons(2, new Cons(3, new Nil())))
nth(2, list)

//nth(-1, list)
// Functions as object
// Funciton type A=>B is an abreviation for scala.Function1[A, B]
// which is defined as
// package scala
// trait Function1[A, B] {
//  def apply(x: A): B
// }

// Currently scala functions only accepts max 22 parameters

// An anonymous function such as x=>x*x is expanded to
// { class AnonFun extends Function1[Int, Int] {
//    def apply(x: Int) = x * x
//   }
//   new AnonFun
// }

// so f(a, b) = f.apply(a, b)

// but wait isn't the apply method also a function, so it would
// expand to a Function1 which would have an apply method and that
// apply method will also be a function and cycle continues
// So to tackle this the functions are not objects but if a function
// is used in a place where function type is expected it is converted
// automatically to function value (x: Int) => f(x) in technically
// terms it is called eta expansion
// Creating a list object such that users can create a new List
// using List(1,2,3)
object List3 {
  // As List(1, 2) = List.apply(1, 2) so we need to override the
  // apply method
  def apply() = new Nil

  def apply[T](x1: T) = new Cons(x1, new Nil)

  def apply[T](x1: T, x2: T) = new Cons(x1, new Cons(x2, new Nil))

  def apply[T](x1: T, x2: T, x3: T) = new Cons(x1, new Cons(x2,
    new Cons(x3, new Nil)))
}
// Bounds:
// Type upper bound:
// Makes sure only a sub class can be passed to this function
def assertAllPos[S <: Inset](r: S): S = ???

// S <: T means: S is a subtype of T
// S >: T means: S is a supertype of T, or T is a subtype of S
// we can also mix them like
// [S >: NonEmpty <: IntSet]
// S is restricted between NonEmpty and Inset

// Covariance:
// If S <: T then Array[S] <: Array[T] is called covariance
// SCALA DOES NOT SUPPORT COVARIANCE
// see video subtyping and generics 4.3 for reason but for rough
// idea by supporting covariance you exchange a compile type
// error with runtime error and you have to explicitly check on
// runtime for array type and using generics you can solve the
// problems for which covariance was required
// Liskov Substitution Principle:
// If A <: B, then everything one can to do with a value of
// type B one should also be able to do with a value of type A.
// Implementation of natural numbers using objects
// The below implementation are called Peano numbers and we can
// construct other data types from Peano numbers
abstract class Nat {
  def isZero: Boolean

  def predecessor: Nat

  def successor: Nat = new Succ(this)

  def +(that: Nat): Nat
  def -(that: Nat): Nat
}

object Zero extends Nat {
  override def isZero: Boolean = true

  override def +(that: Nat): Nat = that

  override def -(that: Nat): Nat =
    if (that isZero) this
    else throw new NoSuchElementException
  override def predecessor: Nat = throw new NoSuchElementException
}

class Succ(n: Nat) extends Nat {
  override def isZero: Boolean = false

  override def +(that: Nat) = n + that.successor

  // or we can implement it as following so its tail recursive
  // override def + (that: Nat) = new Succ(n + that)

  override def -(that: Nat) =
    if (that.isZero) this else n - that.predecessor

  override def predecessor: Nat = n
}

// C[T] is parametrized type and A and B are such that A <: B
// if C[A] <: C[B] C is covariant
// if C[B] >: C[B] C is contravariant
// if neither C[A] or C[B] is subtype then C is non-variant

// C[-T] : contravariant
// C[+T] : covariant
// C[T] : non-variant

// [+T] parameter can appear only appear in method results
// [-T] parameter can only appear in method parameters
// [T] parameter can appear anywhere
// So In
// trait Function1[-T, +U] {
//  def apply(x: T): U
// }
// T can only be used in function parameter because its contravariant
// And U can be only function result because it covariant
// You cannot swap the two
// Now lets see this in action
// Previously we made a class for Nil but Nil is always a single
// Instance so it make sense to make an object for it but that can
// not be done because object have single instance they cannot be
// generic so what we can do is that remove generics from Nil but
// then T in List[T] will be undefined so we have to replace T with
// something that can accept all types; Nothing a good candidate
// because it is subtype of all types also if you look at List[T]
// definition return time of head is also T and for tail its List[T]
// and in our new Nil definition its Nothing for all three of them
// and that corresponds to structure of List[T] because Nothing is
// subtype of List[Nothing] but we have to make List[T] covariant
// to make this all work
trait List1[+T] {
  def isEmpty: Boolean

  def head: T
  def tail: List1[T]
}

class Cons1[T](val head: T, val tail: List1[T])
  extends List1[T] {
  def isEmpty = false
}

object Nil1 extends List1[Nothing] {
  def isEmpty = true

  def head: Nothing = throw new NoSuchElementException("Nil.head")

  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

object test {
  val x: List1[String] = Nil1
}

// Now if we want to add a method prepend to our list trait like
//trait List[+T] {
//  def prepend(elem: T): List[T] = new Cons(elem, this)
//}
// This will not work because variance check fails. Why because
// we can list.prepend(Empty) to a list: List[Inset] but we cannot
// list.prepend(Empty) to a list: List[NonEmpty] but according to
// Liskov Substitution principle every sub class is capable of doing
// what a super class is capable of. So to make it work:
// def prepend [U>:T](elem: U): List[U] = new Cons(elem, this)
// So what we did is that we restricted the elem type to super type
// of T so we can only pass a same or super type to the List and
// get List of same or super type.
// Pattern Matching
trait Expr {
  // Pattern matching by defining method inside the class hierarchy
  // In this case we are doing e1.eval because eval is method of trait
  def eval: Int = this match {
    case Number(n) => n
    // we can also do pattern matching on lot of things like
    // we can pattern match on constructor parameters
    case Sum(Sum(e1, Number(_)), Sum(Number(e2), Number(3))) =>
      e1.eval + e2 + 3
    // it will only match the when we have constructor parameters like above
    // _ is wildcard pattern, and you cannot reference the value again.
    // same variable name can appear once in a pattern and variables begin
    // with lowercase letter and constant begin with capital letter
    // if no case is matched MatchError will be thrown
    // and ofcourse we can do simply
    case Sum(e1, e2) => e1.eval + e2.eval
  }
}

case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

// Pattern matching by method outside the class hierarchy
// in this case we are doing eval(e1) because eval is independent method
def eval(e: Expr): Int = e match {
  case Number(n) => n
  case Sum(Number(_), Sum(Number(e1), Number(3))) =>
    e1 + eval(Number(3))
  case Sum(e1, e2) => eval(e1) + eval(e2)
}

// when we are making a lots of classes then object oriented decomposition
// is better because you can simply implement the eval method in your
// subclass and there is no need to touch other code. On the other hand if
// we need to implement lots of methods then pattern matching is better
// because we don't have to change all classes and the newly created method
// only needs to implement the cases for all classes.
// Quiz show with minimum number of brackets
case class Var(s: String) extends Expr

case class Product(e1: Expr, e2: Expr) extends Expr
def showInParen(e: Expr) = e match {
  case Sum(_, _) => "(" + show(e) + ")"
  case _ => show(e)
}
def show(e: Expr): String = e match {
  case Number(n) => n.toString
  case Var(s: String) => s
  case Sum(e1, e2) => show(e1) + " + " + show(e2)
  case Product(e1, e2) => showInParen(e1) + " * " + showInParen(e2)
}
show(Sum(Product(Number(2), Var("x")), Number(3)))
// Lists
val ints = 1 :: 2 :: 3 :: 4 :: 5 :: -1 :: Nil
// Lists have three common methods head, tail and isEmpty
// Insertion Sort
def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => List(x)
  case k :: ks => if (x > k) k :: insert(x, ks) else x :: xs
}
def isort(xs: List[Int]): List[Int] = xs match {
  case List() => List()
  case k :: ks => insert(k, isort(ks))
}
isort(3 :: 31 :: 231 :: 1 :: 2 :: 5 :: Nil)
val n = 2
// List methods
ints.length // size
ints.last // last element of list
ints.init // all list except last
ints.head // first element
ints.tail // all elements except first
ints take n // first n elements of list
ints drop n // all elements except first n
ints apply n // nth element of list
ints(n) // same as above
val ints1 = 1 :: 213 :: 344 :: 92 :: 1 :: Nil
// List utility methods
ints ++ ints1 // concatenate two list and return new one
ints reverse;
ints updated(n, 1) // replace nth element with 1
ints indexOf 1
ints contains 1
// Custom implementation of list methods
def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("init of empty list")
  case List(_) => List()
  case k :: ks => k :: init(ks)
}
def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case List() => ys
  case k :: ks => k :: concat(ks, ys)
}
def reverse[T](xs: List[T]): List[T] = xs match {
  case List() => xs
  case k :: ks => reverse(ks) ::: List(k)
}
def removeAt[T](xs: List[T], n: Int): List[T] =
  (xs take n) ::: (xs drop n + 1)
// Nested pattern match
val a: Int = 1
val b: Int = 1
a match {
  case 1 => b match {
    case 1 => 1
  }
}
// Pairs
val myPair = ("apple", 2)
// pattern binding
val (fruit, myInt) = myPair
// Tuples -- more then 2 elements
val myTuple = ("apple", 2, 3, "orange")
myTuple._4 // returns oranges
// Avoiding nested pattern matching using pairs
(a, b) match {
  case (0, 1) => 1
  case (1, 0) => 1
  case (1, 1) => 2
}
// Higher order functions on lists
def squareList(xs: List[Int]): List[Int] = xs match {
  case Nil => Nil
  case k :: ks => k * k :: squareList(ks)
}
def squareListNew(xs: List[Int]): List[Int] =
  xs map (k => k * k)
def isPos(x: Int) = x > 0
ints filter isPos
ints filterNot isPos
ints partition isPos // return a pair of filter and filterNot
// list of all elements from 0 to k where k is index of first
// element that does not meet criteria
ints takeWhile isPos
// inverse of takeWhile starting from first element that does not
// meet the criteria
ints dropWhile isPos
ints span isPos // pair of take and drop while
// returning consecutive duplicates into Lists
val data = List(1, 1, 1, 2, 2, 1, 3, 3, 1)
def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case k :: ks =>
    val (first, rest) = xs span (x => x == k)
    first :: pack(rest)
}
pack(data)
// encoding/compression
def encode[T](xs: List[T]): List[(T, Int)] =
  pack(xs) map (ks => (ks.head, ks.length))
encode(data)
val gs = 1 :: 2 :: 3 :: Nil
// reduceLeft reduces a non empty list to left
(0 :: gs) reduceLeft (_ + _)
// similar as reduceLeft but can be applied to empty list
// takes an operator and accumulator
(gs foldLeft 0) (_ + _)
// fold and reduceRight
(gs foldRight 0) (_ + _)
// foldRight and foldLeft will give same answer if the operator
// are both associative and commutative but may differ in efficiency
// But there are some cases in which they are not interchangeable
// like one below
// fold right works but foldLeft fails because :: is not function
// on type T
def concat1[T](xs: List[T], ys: List[T]): List[T] =
  (xs foldRight ys) (_ :: _)
//(xs foldLeft ys)(_ :: _)
// Vectors
// vectors are basically very shallow trees
// initially its array of 32 elements
// if greater then 32 then each element in 32 bit array points
// to array of 32 bit so that 32 arrays of 32 size (32*32)
// and similarly it grows like this to 32*32*32 ...
// so access complexity of Vector is log_32(N)
val myVec = Vector(1, 2, 3, 12312, -1)
// all methods of list are available on vectors except ::
// instead of :: we have +:/:+ for vectors
// +: for adding to left and :+ for adding to right
// note : always points to sequence
// vectors like list are immutable
// object creation complexity is log_32(N)
// Vector like list is a sub class of Sequence there are
// also classes Set and Map like Sequence which are sub class of
// Iterable. Array and Strings also act like sequences but they
// are not sub classes of sequence because they come from Java
val arr = Array(1, 2, 34, 2)
val arr1 = Array(1, 2, 34, 2)
arr map (x => x * 2)
val string1 = "some string"
string1 filter (c => c.isUpper)
// Another type of sequence is range
// range is simply evenly divided integers
1 until 5 // 5 not included
1 to 5 // 5 included
1 to 6 by 2 // step size is 2
6 to 1 by -1 // in backward direction
// common methods on sequence
// check if there is an element in seq that matches a predicate
arr exists (e => e > 0)
// true of predicate holds for all
arr forall (e => e < 0)
// create seq of pairs using elements from two seqs
arr zip arr1
// inverse of zip
(arr zip arr1) unzip;
// flatMap applies f(x => List) on a list and concatenates results
arr flatMap (c => List('.', c))
// sum adds all elements
arr sum;
// product of all elements
arr product;
// max of all elements
arr max;
// min of all elements
arr min
// Scalar product
def scalarProduct1(xs: Vector[Double], ys: Vector[Double]): Double =
  (xs zip ys) map (xy => xy._1 * xy._2) sum
// we can rewrite it using pattern matching
def scalarProduct2(xs: Vector[Double], ys: Vector[Double]): Double =
  (xs zip ys) map { case (x, y) => x * y } sum
def isPrime1(n: Int): Boolean = (2 until n) forall (n % _ != 0)
// create pairs of numbers less then m whose sum is prime and
// first element is greater then second
// until: generates a range from 1 to n
// flatten: converts list of lists to a single list with all
// elements concatenated
val m = 5
((1 until m) map (i =>
  (1 until i) map (j => (i, j)))).flatten
// we can also write this as
val flatList = (1 until m) flatMap (i =>
  (1 until i) map (j => (i, j)))
def isPrime(n: Int) = (2 until n) forall (n % _ != 0)
flatList filter (pair => isPrime(pair._1 + pair._2))
// we can express above logic in a more simpler way using for
// expressions
// we can write {} instead of () in for if generators and filters
// needs to varied on multiple lines or alternatively you can use
// semi colons between generators for multiple lines
for {
  i <- 1 until m
  j <- 1 until i
  if isPrime(i + j)
} yield (i, j)
val ls1 = 1 :: 2 :: 3 :: Nil
val ls2 = 2 :: 21 :: 1 :: Nil
// scalar product
// zip: returns a list of pairs containing element from each list
(for ((x, y) <- ls1 zip ls2) yield x * y) sum
// Sets:
val fruits = Set("apple", "banana", "ballon", "pear")
val s = (1 to 6) toSet;
// most operations on set are also available on set
fruits map (_ + "s")
s nonEmpty
// All methods on iterables are basically available to all
// collections
// Difference between sets and sequences
// 1 - Sets are unordered
// 2 - Sets cannot contain repetition
// 3 - For sequences fundamental operations are head and tail
// but for sets the fundamental operations is contains
// Queens Problems: place n queens on a chess board of n*n such
// that no queen threatens other
def queens(n: Int): Set[List[Int]] = {
  def placeQueens(k: Int): Set[List[Int]] = {
    if (k == 0) Set(List())
    else
      for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens
  }
  def isSafe(col: Int, queens: List[Int]): Boolean = {
    val row = queens length
    val cells = (row - 1 to 0 by -1) zip queens
    cells forall {
      case (r, c) =>
        col != c && math.abs(col - c) != (row - r)
    }
  }
  placeQueens(n)
}

def show(queens: List[Int]) = {
  val lines = for (col <- queens reverse)
    yield Vector.fill(queens.length)("* ") updated(col, "X ") mkString;
  "\n " + (lines mkString "\n ")
}
(queens(8) take 3 map show) mkString "\n"

// for is similar to sql queries in many ways
// lets see how we can use it for quering our database of books
case class Book(title: String, authors: Set[String])

val books = Set(
  Book("a", Set("1", "9")),
  Book("b", Set("3", "8")),
  Book("c", Set("4", "2")),
  Book("d", Set("5", "6")),
  Book("e", Set("1", "7")),
  Book("f", Set("3", "5")),
  Book("g", Set("3", "10"))
)
// All books whose authors contains work "1"
for {
  b <- books
  a <- b.authors
  if a contains "1"
} yield b.title
// List of all authors that have more than single books
for {
  b1 <- books
  b2 <- books
  if b1.title < b2.title
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield a1
// Map
// Maps keys to values
val counMap = Map(1 -> 2, 3 -> 4, 2 -> 23)
// map extends class Iterable so map supports similar operations
val valGreaterthenKey = counMap map {
  case (k, v) => v > k
}
// map are also functions
// so maps can be used anywhere the function can be
// function call
counMap(1)
// but if a key does not exsist in map key not found error is thrown
// To avoid this we can use get function which returns None or Some
counMap get 21
counMap get 2
// None and Some have type Optional and it is a standard type in scala lib
// and are case classes extending trait Option so we can use pattern matching
counMap get 3 match {
  case None => 0
  case Some(x) => x
}
// Sorting
val myList = List(1, 2, 3, 4, 5, 6, -9, -2, 100)
myList sorted;
myList sortWith (_ > _)
// Group By
// Converts a collection to map using some discrimination functions that
// groups similar items
fruits groupBy (_ head)

// Map default value
val p3 = counMap withDefaultValue "unknown"
val p4 = Map(1 -> 2, 2 -> 3) withDefaultValue 0

// Representing polynomials as maps
class Polynomial(terms: Map[Int, Double]) {
  // Passing an arbitary number of parameters
  // This allow us to skip writing intermediate datastructre Map every time
  // we initialize
  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  val vals = terms withDefaultValue 0.0

  def +(other: Polynomial) =
    new Polynomial(vals ++ (other.vals map adjust))

  def adjust(term: (Int, Double)) = {
    val (exp, coef) = term
    exp -> (coef + vals(exp))
  }

  override def toString =
    (for ((exp, coef) <- vals.toList.sorted.reverse)
      yield coef + "x^" + exp) mkString " + "
}

val p1 = new Polynomial(0 -> 1.2, 1 -> 2.3, 4 -> 3)
val p2 = new Polynomial(Map(1 -> 2.2, 2 -> 2.3, 3 -> 3.4))
p1 + p2

// Defining + using foldRight
class Poly1(terms: Map[Int, Double]) {
  def this(bindings: (Int, Double)*) = this(bindings.toMap)
  val vals = terms withDefaultValue 0.0

  def +(other: Poly1) =
    new Poly1((other.vals foldLeft vals) (addTerm))

  def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
    val (exp, coef) = term
    terms + (exp -> (coef + terms(exp)))
  }

  override def toString =
    (for ((exp, coef) <- vals.toList.sorted.reverse)
      yield coef + "x^" + exp) mkString " + "
}

val p5 = new Poly1(0 -> 1.2, 1 -> 2.3, 4 -> 3)
val p6 = new Poly1(Map(1 -> 2.2, 2 -> 2.3, 3 -> 3.4))
p5 + p6

// Streams a sub class of sequence
// Lazily evaluates
val stream1 = Stream.empty
val stream2 = Stream.cons(1, Stream.cons(2, Stream.empty))
val stream3 = Stream(1, 2, 3)
// Or you can convery any collection to stream
(1 to 2000) toStream
// Note the result of stream they have first element and
// instead of second element they have the ? that means that it
// is not computed
// #:: is short form for cons in streams
val stream4 = 1 #:: 2 #:: Stream.empty
// Laziness
// Scala uses strict evaluation by default but you can use
// lazy evaluation also and is appreciated.
// Laziness mean 2 things
// 1 - Don't evaluate until needed
// 2 - Don't evaluate twice
// if you remember if we created something using def
// it is not evaluated until def is called so you may think
// lazy val and def are same thing well they are not because
// def is reevaluated every time it is called but lazy stores
// the value of first evaluationn and then gives that same value
// whenever its asked
def expr = {
  val x = {
    print("x"); 1
  }
  lazy val y = {
    print("y"); 2
  }
  def z = {
    print("z"); 3
  }
  z + y + x + z + y + x
}
expr
// implementing square root using streams
def sqrtStream(x: Double): Stream[Double] = {
  def improve(guess: Double) = (guess + x / guess) / 2
  lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
  guesses
}

sqrtStream(4) take 10 toList