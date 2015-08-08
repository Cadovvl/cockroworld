package com.cocroworld.domain

import common.{IQuestInfo, IAbilities}

class AbilitiesModel extends CockroachModel with IAbilities {
  var abilities: Gene = new Gene()

  def train(): Unit = {
     //как то будет реализовано и использовано
  }

  override def getStrength: Double = 1

  override def getLuck: Double = 1

  override def getSneak: Double = 1

  override def getHP: Double = 1

  override def getIntellegence: Double = 1
}
