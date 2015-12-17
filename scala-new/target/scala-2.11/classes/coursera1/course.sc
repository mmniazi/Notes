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
trait List[T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T])
  extends List[T] {
  def isEmpty = false
}

class Nil[T] extends List[T] {
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
def nth[T](n: Int, list: List[T]) {
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
object List {
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

case class Var(s: String) extends Expr

case class Product(e1: Expr, e2: Expr) extends Expr

def show(e: Expr): String = e match {
  case Number(n) => n.toString
  case Var(s: String) => s
  case Sum(e1, e2) => "(" + show(e1) + " + " + show(e2) + ")"
  case Product(e1, e2) => show(e1) + " * " + show(e2)
}

show(Product(Sum(Number(2), Var("x")), Number(3)))