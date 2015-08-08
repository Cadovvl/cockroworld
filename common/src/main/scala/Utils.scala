import scala.util.Random

object Utils {
  var random = new Random()

  def choose(args: Array[Double]): Int = {
    val sum = args.fold(0.0)(_+_)
    val choise = (random.nextDouble() * sum) % sum
    var check = 0.0
    for (i <- 0 to args.length) {
      check += args(i)
      if (choise <= check)
        return i
    }
    args.length
  }
}
