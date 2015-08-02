import scala.util.Random

object Main {
  def main(args: Array[String]): Unit = {
    val player1 = new TestPlayer()
    val player2 = new TestPlayer()

    val game = new EasyFight(Array(player1, player2))

    game.play()
  }
}
