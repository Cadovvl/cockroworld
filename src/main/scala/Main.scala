import scala.util.Random

class TestObj {

  object T {
    var i = 0
  }

}

object Main {
  def main(args: Array[String]): Unit = {
    val player1 = new TestPlayer()
    val player2 = new TestPlayer()

    val easyCB = new CallbackFight(Array(player1, player2),
      FightCallbackFactory.EASY_FIGHT_SET)

    easyCB.play()
  }
}
