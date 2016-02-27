// sum of all multiples of 3 and 5 below 1000
(1 until 1000) filter (x => x % 3 == 0 || x % 5 == 0) sum