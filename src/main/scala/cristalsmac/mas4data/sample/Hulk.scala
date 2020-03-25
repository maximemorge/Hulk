//Copyright (C) Quentin BAERT and Maxime MORGE 2019
package cristalsmac.mas4data.sample
import akka.actor._
import akka.actor.Actor
/**
  * The messages
  */
sealed trait HulkMessage
object Flick extends HulkMessage
object Warning extends HulkMessage
object HulkSmash extends HulkMessage
object Calm extends HulkMessage

/**
  * The state of Bruce
  */
sealed trait BruceState
case object Banner extends BruceState
case object Hulk extends BruceState

/**
  * The mind of Bruce
  * @param patience is an integer representation of his memory
  */
final case class Mind(patience: Int = 3)

/**
  * Bruce is an agent, i.e. an actor with a behaviour and a state of mind
  */
class Bruce extends Actor with FSM[BruceState, Mind] {
  // Bruce starts as Banner with patience
  startWith(Banner, Mind())

  // When Bruce is Banner, the flicks decrease his patience until he becomes Hulk
  when(Banner) {
    case Event(Flick, mind) if mind.patience > 0 =>
      println("Bruce: -Hum...")
      stay using Mind(mind.patience-1)
    case Event(Flick, mind) if mind.patience == 0 =>
      println("Bruce: -Grr...")
      goto(Hulk) using Mind(0) replying Warning
  }

  // When Bruce is Hulk, flicks imply a smash and he calms down, i.e. become Banner
  when(Hulk) {
    case Event(Flick, _) =>
      stay replying{
        sender ! HulkSmash
        self ! Calm
      }
    case Event(Calm, _) =>
      goto(Banner) using Mind()
  }

  // Monitoring
  onTransition {
    case from -> to=> println(s"Bruce was $from and he becomes $to")
  }

  // Initialize Bruce
  initialize()
}
