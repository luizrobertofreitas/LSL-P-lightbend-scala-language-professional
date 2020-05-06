package com.lightbend.training.scalatrain

case class Train(trainInfo: TrainInfo, schedule: Seq[(Time, Station)]) {
  require(schedule.size > 1, "Must have at least 2 elements")

  val stations: Seq[Station] = schedule.map(_._2)

  def timeAt(station: Station): Option[Time] =
    schedule.filter(s => s._2 == station).map(s => s._1).headOption
}
