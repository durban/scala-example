
package com.example

import akka.http.scaladsl.server.Directives._
import akka.typed.AskPattern._
import akka.util.Timeout
import scala.concurrent.ExecutionContext
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import scala.util.Try

object Main {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

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
    val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", port)
    println(s"Server listening at http://0.0.0.0:$port/\nPress RETURN to stop...")
    scala.io.StdIn.readLine()
    bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
  }
}
