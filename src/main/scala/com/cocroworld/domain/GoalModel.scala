package com.cocroworld.domain

/**
 * Created by Anna on 7/27/2015.
 */
class GoalModel extends CockroachModel {
  var goal: Gene = new Gene()

  def takeDecision(quest: Quest): Action = {
    new Action()
  }

  def evaluateResult(result: ActionResult): Int = {
       0
  }
}
