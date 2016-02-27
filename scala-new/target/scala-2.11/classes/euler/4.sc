/*
* A palindromic number reads the same both ways.
* The largest palindrome made from the product of two
* 2-digit numbers is 9009 = 91 × 99
* Find the largest palindrome made from the product
* of two 3-digit numbers.
* */

def palindromic(n: Int) = {
  val string = n toString;
  (string.length == 6) && (string.substring(4) == string.substring(4, 6))
}

val numbers =
  for {
    i <- 999 until 100 by -1
    j <- 999 until 100 by -1
    if palindromic(i * j)
  } yield i * j

numbers