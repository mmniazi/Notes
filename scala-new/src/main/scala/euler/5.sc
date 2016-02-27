/*
* 2520 is the smallest number that can be divided by each
* of the numbers from 1 to 10 without any remainder.
* What is the smallest positive number that is evenly
* divisible by all of the numbers from 1 to 20?
* */

val numbers = for {
  i <- 1 to 999999999
  if (1 to 20) forall (i % _ == 0)
} yield i

numbers min