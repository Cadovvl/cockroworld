package com.cocroworld.domain

/**
 * Created by Anna on 7/27/2015.
 */
class AbilitiesModel extends CockroachModel {
  var abilities: Gene = new Gene()

  def train(quest: Quest, action: Action, goalModel: GoalModel): Unit = {
    train(cockroach => {
      val resultForCurrentAbility = quest.getExpectedResult(cockroach.gene, action)
      goalModel.evaluateResult(resultForCurrentAbility)
    })
  }
}
