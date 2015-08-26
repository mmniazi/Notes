/* Bismillah Alrehman Alrahim */

// Printing:
print("Printing without line break")
println("Printing with line break")

// Defining Variables:
val a = 'a' // Type infered
val a1:Double = 1 // Type specified
val words:String = "A quick brown fox jumps over the lazy dog"

// Multi line string with automatic character escaping.
val lines = """first line
second line
third line"""

// Compound Expressions
val c = {
  val d = 2
  val e = {
    2>3
  }
  val f = d*2
  f
}

// Methods
def multiplyByTwo(x:Int):Int = {
  x*2 // Last line is Return value
}
val result = multiplyByTwo(5)
println(result)

// Test using assert
def test(x:Int, y:Double, expected:Double):Unit = {
  val result = x * y
  assert(result == expected, "Expected " + expected + " Got " + result)
  println("result: " + result)
}
test(1,2,2)

// Classes and Objects
val range = Range(0,10) // Object of Range class
val reversed = range.reverse // Calling method on object
println(reversed)

// Creating classes
class Baby
class Donkey

val smallBaby = new Baby
val bigDonkey = new Donkey

println(smallBaby)
println(bigDonkey)

class Noob {
  val noobnessfactor = -1
  def doNoobness(x:Int):Int = {
    x + noobnessfactor
  }
}
val noob = new Noob
println(noob.doNoobness(3))

// Importing
//First Method
//import util.Random
//import util.Properties
// Second Method
//import util.Random, util.Properties
//Third Method
//import util.{Random, Properties}
// Forth Method we can change names of imports
import util.{ Random => Ran, Properties}
// Fifth Method we can import everything in package
// import util._
val r = new Ran
// Or we can use fully qualified name
val r1 = new util.Random
println(r.nextDouble)
println(r1.nextInt)

// Importing custom package
// You may need to run scala -nocompdaemon lesson.scala to make it work due to bug in scala 2.11
import samplePackage._
val randomInt = new sampleClass().sampleMethod
println(randomInt)

// TDD (Test driven development)
import  com.atomicscala.AtomicTest._
// First create test btw "is" is a function in AtomicTest and this notation with spaces is called infix notaion.
calcBMI(160, 68) is "Normal weight"
calcBMI(100, 68) is "Under weight"
calcBMI(200, 68) is "Over weight"
// Then write code such that they fullfill tests
def calcBMI(lbs:Double, height:Double): String = {
  val bmi = lbs/(height*height)*703.07
  if(bmi < 18.5) "Under weight"
  else if (bmi < 25) "Normal weight"
  else "Over weight"
}

// For loops
var resultLoops = ""
// Using to Inclusive of end point
for(i <- 0 to 9) {resultLoops += i + " "}
// Using until exclusive of end point
for(i <- 0 until 10){}
// Using Range
for(i <- Range(0, 10)){}
for(i <- Range(0, 20, 2)){}
// Inclusive adds end point to range
for(i <- Range(0, 20, 2).inclusive){}
// Type is automatically infered for i in all these for's

// Vectors
val v1 = Vector(1,3,5,7,9)
v1 is Vector(1,3,5,7,9)
v1(4) is 9
var resultVectors = ""
for(i <- v1) {resultVectors += i + " "}
resultVectors is "1 3 5 7 9 "
// Some vector functions
v1.reverse // -- immutable
v1.sorted // -- immutable
v1.head
v1.tail
// Remember all vector objects are created with factories
/* ---------------- End of Atomic Scala Book ---------------- */

/* ---------------- Start of Programming in Scala ---------------- */
// While loops -- Not a good practice
val args = Array("Scala", "is", "Good")
var i = 0
  while (i < args.length) {
    println(args(i))
    i += 1
  }
// As an alternative of above while code we can use for
for (arg <- args)
    println(arg)
// Or even better foreach
args.foreach(println)
// Arrays --mutable
val greetStrings = new Array[String](3)
greetStrings(0) = "Hello"
greetStrings(1) = ", "
greetStrings(2) = "world!\n"
for (i <- 0 to 2)
  print(greetStrings(i))
// Or a better version using factories
val numNames = Array("zero", "one", "two")

// List Scala --immutable
val oneTwoThree = List(1, 2, 3)
// List concatenation ":::"
// Creates a new list by concatenating one list to another
val oneTwo = List(1, 2)
val threeFour = List(3, 4)
val oneTwoThreeFour = oneTwo ::: threeFour
println(""+ oneTwo +" and "+ threeFour +" were not mutated.")
println("Thus, "+ oneTwoThreeFour +" is a new list.")
// Remember normally all methods are called on left element like a + b is a.+(b) but methods ending with ":" are called on element of right so ":::" is called as threeFour.:::(oneTwo)

// Cons: "::" is used prepend element to list i.e add element to start.
val twoThree = List(2,3)
val oneTwoThreeNew = 1 :: twoThree
println(oneTwoThreeNew)
// It is also used as a short syntax for creating lists
val abcdList = "a" :: "b" :: "c" :: Nil
// Nil is used to specifiy empty node and is last element of List

// Tuples: Like lists but can contain different types of elements
var tuple = (99, "Nanananan")
println(tuple._1)
println(tuple._2)

// Set can be both mutable and immutable so we can decide which one we want to use
// Here is default implementation of Set --immutable
import scala.collection.immutable.HashSet
val hashSet = HashSet("Tomato","Chilly")
println(hashSet + "barbique") // Note set has no particular order

// Map can also be mutable or immutable
// Mutable Map
// import scala.collection.immutable.Map --notrequired as its default import
val hashMap = Map("X" -> "Secret Island", "Lalalalal"->"StupidDog!!")
println(hashMap("X"))
// Or you can use mutable Map
import scala.collection.mutable.Map
val noobMap = Map[Int, String]()
noobMap += (1 -> "Noob")
noobMap += (2 -> "ExtraNoob")
println(noobMap(1))

// Reading Files
import scala.io.Source
for(line <- Source.fromFile("FunProgGuide").getLines.toList.slice(0,5))
  print(line.length + " " + line)

// Reduce left: Applies method to first to elements of collection and then applies to result of first two and third and reduces collection to an element.
// Forexample if we want to find a longest line in a List lines.
val longestLine = lines.reduceLeft(
  (a, b) => if (a.length > b.length) a else b
)
