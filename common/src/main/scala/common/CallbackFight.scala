package common

import scala.util.Random

/**
 * @param players - список игроков
 * @param callbacks - возможные действия;
 *
 *                  Обобщенная версия EasyFight
 *
 *                  Изначально у каждого играка появляется некоторое количество здоровья,
 *                  пропорциональное значению его hp.
 *
 *                  Каждый ход выбираетюся два игрока: атакующий и защищающийся.
 *                  Вероятность быть выбранным обратно пропорциональна страху.
 *
 *
 *                  Дальше происходит последовательность действий, определенная
 *                  списком входящих событий
 *
 *                  Игра заканчивается, когда у одного из игроков здоровье падает в минус
 *
 *                  Как выдавать плюшки за победу - пока все-еще хз
 *
 */
class CallbackFight(val players: Array[IPlayer],
                    val actions: Array[CallbackFight => Boolean])
  extends Game {

  class Gamer(val player: IPlayer) {
    var hp = player.getHP * BASE_HP
  }

  val random = new Random()
  val BASE_HP = 50.0

  var gamers: Array[Gamer] = Array()
  var fears: Array[Double] = Array()
  var attacker: Int = 0
  var defender: Int = 0

  override protected def step(): Unit = {
    move()
    act()
  }

  private def move(): Unit = {
    attacker = Utils.choose(fears)
    defender = Utils.choose(fears)
  }

  private def act(): Unit = {
    println(players(attacker).getName + " решил ударить " + players(defender).getName)

    def actI(i: Int): Unit = {
      if (i >= actions.length)
        defaultAction()
      if (!actions(i)(this))
        actI(i + 1)
    }

    actI(0)

    println("\n" + "#" * 40)
  }

  override protected def init(): Unit = {
    gamers = for (p <- players) yield new Gamer(p)
    fears = for (p <- players) yield p.getFear
  }

  override protected def isEnded(): Boolean = gamers.exists(_.hp <= 0)

  override protected def end(): Unit = {
    gamers.withFilter(_.hp < 0)
      .foreach(p => println("Игрок " + p.player.getName + " слегка окочурился..."))
    gamers = Array()
    fears = Array()
  }

  protected def defaultAction(): Unit = {
    println("Внезапно на арену упал небольшой метиорит, и долбанул по всем")
    gamers.foreach(_.hp -= 1.0)
  }
}

object FightCallbackFactory {
  // Попытка ударить самого себя
  val getSelfAttackAction: CallbackFight => Boolean = f => {
    if (f.attacker == f.defender) {
      println(f.players(f.attacker).getName
        + " вовремя понял, что захотел ударить себя...")
      true
    } else
      false
  }

  // Удачливый игрок может увернуться
  def getMissByLuckAction(baseAttack: Double = 10.0): CallbackFight => Boolean = f => {
    if (Utils.choose(
      Array(
        f.players(f.defender).getLuck,
        f.players(f.defender).getLuck + baseAttack)) == 0) {
      println(f.players(f.defender).getName +
        " сумел увернуться от атаки " + f.players(f.attacker).getName)
      true
    } else
      false
  }


  def getDamageAction(baseAttack: Double = 5.0): CallbackFight => Boolean = f => {
    val dmg = (baseAttack + f.random.nextDouble()
      % baseAttack) * f.players(f.attacker).getStrength
    f.gamers(f.defender).hp -= dmg
    println(f.players(f.attacker).getName +
      " наносит " + dmg
      + " урона " + f.players(f.defender).getName
      + "; Оставшееся здоровье:")
    f.gamers.foreach(p => print(p.player.getName + " : " + p.hp + "; "))
    true
  }

  val EASY_FIGHT_SET = Array(FightCallbackFactory.getSelfAttackAction,
    FightCallbackFactory.getMissByLuckAction(),
    FightCallbackFactory.getDamageAction())
}

object CallbackFight {
   def getQuestInfo():IQuestInfo  = {
       new SimpleQuestInfo
   }
}




