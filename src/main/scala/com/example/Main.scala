
package com.example

import akka.http.scaladsl.server.Directives._
import akka.typed.AskPattern._
import akka.util.Timeout
import scala.concurrent.ExecutionContext
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import scala.util.Try
import scala.util.Success
import scala.util.Failure
import akka.event.Logging

object Main {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val log = Logging(system, "Main")

  val route =
    path("hello") {
      get {
        complete {
          "Hello, World!\n"
        }
      }
    }

  def main(args: Array[String]): Unit = {
    val port = (for {
      s <- sys.env.get("OPENSHIFT_SCALA_PORT")
      port <- Try(s.toInt).toOption
    } yield port).getOrElse(8080)
    Http().bindAndHandle(route, "0.0.0.0", port) andThen {
      case Success(r) =>
        log.info(s"Listening at ${r.localAddress}")
      case Failure(ex) =>
        log.error(ex, s"Failed to bind to ${port}")
        system.terminate()
    }
  }
}
