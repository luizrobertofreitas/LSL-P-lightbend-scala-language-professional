package com.lightbend.training.scalatrain

abstract sealed class TrainInfo {
  def number: Int
}

case class RegionalExpress(number: Int) extends TrainInfo

case class BavarianRegional(number: Int) extends TrainInfo

case class InterCityExpress(number:Int, hasWifi: Boolean = false) extends TrainInfo