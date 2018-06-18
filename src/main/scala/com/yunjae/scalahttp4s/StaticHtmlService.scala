package com.yunjae.scalahttp4s

import java.io.File

import cats.effect._
import org.http4s.dsl.io._
import org.http4s.{HttpService, StaticFile}

import scala.io.Source

/**
  * @author
  */
object StaticHtmlService {
  val service= HttpService[IO] {
    case request @ GET -> Root / fileName if fileName.endsWith(".html") =>
      StaticFile
        .fromResource("/" + fileName, Some(request))
        .getOrElseF(NotFound())
  }
}
