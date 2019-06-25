//Copyright (C) Maxime MORGE 2019
package cristalsmac.mas4data.sample

import akka.actor._
import akka.actor.{Actor, ActorRef}
import scala.concurrent.duration._

/**
  * The state of Reckless Agent
  */
sealed trait RecklessState
case object Active extends RecklessState

/**
  * Reckless agent has behaviour a single-state machine which counts injured
  * @param bruce is his partner 
  */
class Reckless(bruce: ActorRef) extends Actor with FSM[RecklessState, Int] with Stash {

  startWith(Active, stateData = 0)

  // When the reckless agent is active, he flicks Bruce each second
  when(Active, stateTimeout = 1 second) {
    case Event(Warning, _)  =>
      println("Reckless: -What ?!")
      stay
    case Event(HulkSmash, injured)  =>
      println("Reckless: -Ouch !")
      stay using injured + 1
    case Event(StateTimeout, _) =>
      println("Reckless: -Take this !")
      bruce ! Flick
      stay
    case Event(event, injured) =>
      println(s"Reckless: -I do not understand this ${event.getClass} in state $stateName/$injured")
      stay
  }

  // Initialize Reckless agent
  initialize()
}