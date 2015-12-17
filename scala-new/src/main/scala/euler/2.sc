// fibonacci sequence until sum of numbers is less then
// 4000000
def recursive(i1: Int, i2: Int, sum: Int): Int = {
  val i3 = i1 + i2
  if (i3 < 4000000) {
    if (i3 % 2 == 0) recursive(i2, i3, sum + i3) else recursive(i2, i3, sum)
  } else sum
}

recursive(1, 2, 2)
