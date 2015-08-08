import com.cocroworld.domain.Person
import common.{QuestDecision, CallbackFight, IPlayer, FightCallbackFactory}

import scala.util.Random

class TestObj {

  object T {
    var i = 0
  }

}

object Main {
  def main(args: Array[String]): Unit = {
    val player1:IPlayer = new Person("First great player")
    val player2:IPlayer = new Person("Simple player")
    val player3:IPlayer = new Person("Third player")

    val possiblePlayers = Array(player1, player2, player3)
    val participatedPlayers = possiblePlayers
      .filter(_.decideAboutQuest(CallbackFight.getQuestInfo()) == QuestDecision.PARTICIPATE)

    val easyCB = new CallbackFight(participatedPlayers,
      FightCallbackFactory.EASY_FIGHT_SET)

    easyCB.play()
  }
}
