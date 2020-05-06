package com.lightbend.training.scalatrain

import play.api.libs.json.{JsNumber, JsObject, JsValue}

import scala.util.Try

object Time {
  def fromJson(js: JsValue): Option[Time] = {
    for {
      hours <- Try((js \ "hours").as[Int])
      minutes <- Try((js \ "minutes").as[Int]).recover { case _: Exception => 0 }
    } yield Time(hours, minutes)
  }.toOption
}

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {
  require(hours > -1 && hours < 24, "Hours out of range [0, 23]")
  require(minutes > -1 && minutes < 60, "Minutes out of range [0, 59]")
  val asMinutes: Int = hours * 60 + minutes

  def -(that: Time): Int = minus(that)

  def minus(that: Time): Int = Math.abs(that.asMinutes - this.asMinutes)

  def unary_!(): Boolean = asMinutes  > 0

  override def toString = f"$hours%02d:$minutes%02d"

  override def compare(that: Time): Int =
    this.asMinutes - that.asMinutes
    // if (this.asMinutes > that.asMinutes) 1 else if (this.asMinutes < that.asMinutes) -1 else 0
    def toJson: JsValue = JsObject(Map("hours" -> JsNumber(hours), "minutes" -> JsNumber(minutes)))
}