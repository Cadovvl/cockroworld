import java.util

import scala.util.Random

/**
 *
 * @param Player1
 * @param Player2
 *
 * Изначально у каждого играка появляется некоторое количество здоровья,
 * пропорциональное значению его hp.
 *
 * Каждый ход выбирается один игрок.
 * Вероятность быть выбранным обратно пропорциональна страху.
 *
 * Игрок наносит удар, пропорционально значению его силы.
 * Второй игрок может увернуться, с вероятностью, основанной на его удаче.
 * Ситуация повторяется до тех пор, пока у одно игрока не окажется отрицательное здоровье.
 *
 * Как выдавать плюшки за победу - хз
 *
 */

class EasyFight(val players: Array[IPlayer]) extends Game {
  val random = new Random()
  val BASE_UNLUCKY = 10.0
  val BASE_HP = 50.0
  val BASE_ATTACK = 5.0

  class Gamer(val player: IPlayer) {
    var hp = player.getHP() * BASE_HP
  }

  var gamers: Array[Gamer] = Array()
  var fears: Array[Double] = Array()
  var attacker: Int = 0
  var defender: Int = 0

  override protected def move(): Unit = {
    attacker = Utils.choose(fears)
    defender = Utils.choose(fears)
  }

  override protected def init(): Unit = {
    gamers = for (p <- players) yield new Gamer(p)
    fears = for (p <- players) yield p.getFear()
  }

  override protected def isEnded(): Boolean = gamers.exists(_.hp <= 0)

  override protected def end(): Unit = {
    for (i <- 0 to gamers.length-1)
      if (gamers(i).hp < 0)
        println("Игрок " + i + " слегка окочурился...")
    gamers = Array()
    fears = Array()
  }

  override protected def act(): Unit = {
    println("Игрок " + attacker + " решил ударить игрока " + defender)
    if (attacker == defender) {
      println("Игрок " + attacker + " вовремя понял, что захотел ударить себя...")
    } else if (Utils.choose(
      Array(
        players(defender).getLuck(),
        players(defender).getLuck() + BASE_UNLUCKY)) == 0) {
      println("Игрок " + defender + " сумел увернуться от атаки " + attacker)
    } else {
      val dmg = (BASE_ATTACK + random.nextDouble() % BASE_ATTACK) * players(attacker).getStrength()
      gamers(defender).hp -= dmg
      println("Игрок " + attacker +
        " наносит " + dmg
        + " урона игроку " + defender
        + "; Оставшееся здоровье:")
      gamers.foreach(p => print("hp: " + p.hp + "; "))
      println("\n" + "#" * 40 )
    }
  }
}