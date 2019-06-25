//Copyright (C) Maxime MORGE 2019
package cristalsmac.mas4data.sample

import akka.actor._
import scala.io.StdIn

/**
  * Test Bruce
  */
  object Main extends App {

    // Create the actor system
    val system: ActorSystem = ActorSystem("PlayHulk")
  
    try {
      // Create Bruce and the reckless agent
      val bruce: ActorRef = system.actorOf(Props(classOf[Bruce]))
      system.actorOf(Props(classOf[Reckless], bruce))
      println(">>> Press ENTER to exit <<<")
      StdIn.readLine()
    } finally {
      system.terminate()
    }
  }