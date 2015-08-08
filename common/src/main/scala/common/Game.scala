package common


abstract class Game {

  protected def init(): Unit

  protected def step(): Unit

  protected def isEnded(): Boolean

  protected def end(): Unit

  def play(): Unit = {
    init()
    while (!isEnded()) {
      step()
    }
    end()
  }
}
