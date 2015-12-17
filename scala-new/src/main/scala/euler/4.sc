/*
* A palindromic number reads the same both ways.
* The largest palindrome made from the product of two
* 2-digit numbers is 9009 = 91 × 99
* Find the largest palindrome made from the product
* of two 3-digit numbers.
* */

val numbers =
  for {
    i <- 100 to 999
    j <- 100 to 999
  } yield i * j

def palindromic(n: Int) = {
  val string = n toString;
  string.substring(4) == string.substring(4, 6)
}

numbers max