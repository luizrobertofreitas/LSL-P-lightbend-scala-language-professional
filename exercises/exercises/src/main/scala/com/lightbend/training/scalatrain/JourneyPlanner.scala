package com.lightbend.training.scalatrain

case class JourneyPlanner(trains: Set[Train]) {
  require(trains.nonEmpty, "Trains array size should not be 0")
  val stations: Set[Station] = trains.flatMap(_.stations)

  def trainsAt(station: Station): Set[Train] = trains.filter(_.stations.contains(station))

  def stopsAt(station: Station): Set[(Time, Train)] = for (
    ts <- trains;
    // (ttm, tts) <- ts.schedule if tts == station
    ttm <- ts.timeAt(station)
  ) yield ttm -> ts

  def isShortTrip(from: Station, to: Station): Boolean =
    trains.exists(t => t.stations.dropWhile(_ != from) match {
      case `from` +: `to` +: _ => true
      case `from` +: _ +: `to` +: _ => true
      case _ => false
    })

  def isShortTripNonMatch(from: Station, to: Station): Boolean =
    trains.exists(t => t.stations.dropWhile(s => s == from).slice(1, 3).contains(to))
}
