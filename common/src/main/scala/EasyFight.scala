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
    var hp = player.getHP * BASE_HP
  }

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
    if (attacker == defender) {
      println(players(attacker).getName + " вовремя понял, что захотел ударить себя...")
    } else if (Utils.choose(
      Array(
        players(defender).getLuck,
        players(defender).getLuck + BASE_UNLUCKY)) == 0) {
      println(players(defender).getName + " сумел увернуться от атаки " + players(attacker).getName)
    } else {
      val dmg = (BASE_ATTACK + random.nextDouble() % BASE_ATTACK) * players(attacker).getStrength
      gamers(defender).hp -= dmg
      println(players(attacker).getName +
        " наносит " + dmg
        + " урона " + players(defender).getName
        + "; Оставшееся здоровье:")
      gamers.foreach(p => print(p.player.getName + " : " + p.hp + "; "))
    }
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
}