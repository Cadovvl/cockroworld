package common

import common.QuestDecision.QuestDecision


trait IPlayer extends IAbilities with IGoals with IInfo {

  def decideAboutQuest(quest: IQuestInfo):QuestDecision
  def acceptQuestResult

}
