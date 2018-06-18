package com.yunjae.scalahttp4s

import cats.effect.{Effect, IO}
import fs2.StreamApp
import fs2.StreamApp.ExitCode
import org.http4s.server.blaze.BlazeBuilder

import scala.concurrent.ExecutionContext

object MainServer extends StreamApp[IO] {
  import scala.concurrent.ExecutionContext.Implicits.global

  def stream(args: List[String], requestShutdown: IO[Unit]): fs2.Stream[IO, StreamApp.ExitCode] = ServerStream.stream
}

object ServerStream {

  def helloWorldService = HelloWorldService.service

  def staticHtmlService = StaticHtmlService.service

  def stream(implicit ec: ExecutionContext): fs2.Stream[IO, ExitCode] =
    BlazeBuilder[IO]
      .bindHttp(8080, "0.0.0.0")
      .mountService(helloWorldService, "/hello")
      .mountService(staticHtmlService, "/html")
      .serve
}
