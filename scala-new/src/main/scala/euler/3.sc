import scala.collection.immutable.NumericRange

// The prime factors of 13195 are 5, 7, 13 and 29.
// What is the largest prime factor of the number 600851475143 ?
val n = 600851475143l

//def longStream(i: Long = n - 1): Stream[Long] =
//  if (i == 0) Stream.empty
//  else i #:: longStream(i - 1)
//
//def cal_fac(n: Long) = {
//  for {
//    num <- longStream()
//    if n % num == 0
//  } yield num
//}
//
//def isPrime(n: Long) = (2L until n) forall (n % _ != 0)
//
//cal_fac(n)

// Using recursive function

def fac_rec(num: Long,factors: List[Long]): List[Long] =
  if(num == 0) factors
  else if(n % num == 0) fac_rec(num-1, num :: factors)
  else fac_rec(num -1, factors)

val factors = fac_rec(n-1, Nil)