package com.cocroworld.domain

/**
 * Created by Anna on 7/27/2015.
 */
class Person {
  var goalModel: GoalModel = new GoalModel()
  var abilitiesModel: AbilitiesModel = new AbilitiesModel()

  def performQuest(quest: Quest): Action = {
    val actionToTake = goalModel.takeDecision(quest)

    val questResult = quest.perform(abilitiesModel.abilities, actionToTake)
    actionToTake
  }
}
