package com.cocroworld.domain

import common.QuestDecision.QuestDecision
import common._

import scala.util.Random

class Person(val name: String) extends IPlayer {
  val goalModel: GoalModel = new GoalModel()
  val abilitiesModel: AbilitiesModel = new AbilitiesModel()

  override def decideAboutQuest(quest: IQuestInfo): QuestDecision = {
    //здесь будет реализация на основе goals. Потом.
    val random = new Random()
    val res = random.nextDouble()
    if (res < 0.8) QuestDecision.PARTICIPATE else QuestDecision.REJECT
  }

  override def acceptQuestResult: Unit = {
    //сюда надо будет передать результаты квеста и на основе их что-то сделать, используя goalModel.evaluateQuestResult
  }

  //Надо разобраться как это сделать нормально. Вроде через implicit можно как то
  override def getStrength: Double = abilitiesModel.getStrength

  override def getLuck: Double = abilitiesModel.getLuck

  override def getSneak: Double = abilitiesModel.getSneak

  override def getHP: Double = abilitiesModel.getHP

  override def getIntellegence: Double = abilitiesModel.getIntellegence

  override def getGreed: Double = goalModel.getGreed

  override def getFear: Double = goalModel.getFear

  override def getCommunity: Double = goalModel.getCommunity

  override def getExp: Double = goalModel.getExp

  override def getName: String = name
}
