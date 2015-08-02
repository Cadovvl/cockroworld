
abstract class Game {

  protected def init(): Unit

  protected def move(): Unit

  protected def act(): Unit

  protected def isEnded(): Boolean

  protected def end(): Unit

  def play(): Unit = {
    init()
    while(!isEnded()) {
      move()
      act()
    }
    end()
  }
}
