/*
* Difference between the sum of the squares of the first
* ten natural numbers and the square of the sum is
* 3025 − 385 = 2640
* Find the difference between the sum of the squares of
* the first one hundred natural numbers and the square of
* the sum.
* */

val sqSum = (1 to 100) map (math.pow(_, 2)) sum
val sumSq = math.pow((1 to 100) sum, 2)
sumSq - sqSum