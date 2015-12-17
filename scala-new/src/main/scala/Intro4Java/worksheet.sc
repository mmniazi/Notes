// A tutorial from
// http://www.scala-lang.org/docu/files/ScalaTutorial.pdf

import java.text.DateFormat
import java.text.DateFormat._
import java.util.{Locale, Date}

object HelloWorld {
  def main(args: Array[String]) {
    println("Hello, world!")
  }
}

// To compile use scalac HelloWorld.scala
// To run use scala -classpath . HelloWorld

// Interaction with Java:
def printDate() {
  val now = new Date
  val dateFormat = getDateInstance(LONG, Locale.FRANCE)
  println(dateFormat format now)
}
printDate()
// we can also inherit from java classes and implement
// java interfaces

// even primitives are objects in scala so are the numbers
// and mathematical operations are function calls
// so we can write
1.+(2)
// functions are also objects so we can pass functions
// and return them from functions
def oncePerSec(callback: () => Unit) = {
  // for loop
  for (i <- 1 to 1) {
    callback()
    Thread sleep 1000
  }
}
// Anonymous function
oncePerSec(() => println("Time flies like an arrow"))

// Scala classes have builtin constructor in class
// defination
class Complex(real: Double, imaginary: Double) {
  // we can define accessors as following
  // def re() = real
  // def img() = imaginary
  // but to call these we have to write () at end of re
  // that is tedious we can create functions without
  // parameters instead of zero parameters as
  def re = real

  def img = imaginary

  // we can ovveride using ovveride keyword
  override def toString = "a to String implementation"
}

// Scala.AnyRef is super class of every class whose
// super class is not specified

// TODO: complete pattern matching documentation

// Traits:
// Traits differ from interfaces as they can have
// definations
trait Ord {
  def <(that: Any): Boolean

  def <=(that: Any): Boolean = (this < that) || (this == that)

  def >(that: Any): Boolean = !(this <= that)

  def >=(that: Any): Boolean = !(this < that)
}

class Date(y: Int, m: Int, d: Int) extends Ord {
  def year = y

  def month = m

  def day = d

  override def toString: String = year + "-" + month + "-" + day

  override def equals(that: Any): Boolean =
    that.isInstanceOf[Date] && {
      val o = that.asInstanceOf[Date]
      o.day == day && o.month == month && o.year == year
    }

  def <(that: Any): Boolean = {
    if (!that.isInstanceOf[Date])
      sys.error("cannot compare " + that + " and a Date")
    val o = that.asInstanceOf[Date]
    (year < o.year) ||
      (year == o.year && (month < o.month ||
        (month == o.month && day < o.day)))
  }
}

// Generics
class Reference[T] {
  private var contents: T = _

  def set(value: T) {
    contents = value
  }

  def get: T = contents
}

// Here _ assigns the default value to type which is
// 0 for numeric, false for boolean, () for unit and
// null for all other objects
// We can use generic class as following
val cell = new Reference[Int]
cell.set(13)
println("Reference contains the half of " +
  (cell.get * 2))

