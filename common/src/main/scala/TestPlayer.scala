
class TestPlayer(private val strength_ : Double = 1.0,
                 private val luck_ : Double = 1.0,
                 private val sneak_ : Double = 1.0,
                 private val intellegence_ : Double = 1.0,
                 private val fear_ : Double = 1.0,
                 private val community_ : Double = 1.0,
                 private val exp_ : Double = 1.0,
                 private val greed_ : Double = 1.0,
                 private val hp_ : Double = 1.0) extends IPlayer {
  override def getStrength(): Double = strength_

  override def getLuck(): Double = luck_

  override def getSneak(): Double = sneak_

  override def getIntellegence(): Double = intellegence_

  override def getFear(): Double = fear_

  override def getCommunity(): Double = community_

  override def getExp(): Double = exp_

  override def getGreed(): Double = greed_

  override def getHP(): Double = hp_
}
