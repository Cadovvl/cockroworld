package com.cocroworld.domain

import common.{IGoals}

class GoalModel extends CockroachModel with IGoals {
  var goal: Gene = new Gene()

  def evaluateQuestResult(): Int = {
    0
  }


  override def getGreed: Double = 1

  override def getFear: Double = 1

  override def getCommunity: Double = 1

  override def getExp: Double = 1
}
