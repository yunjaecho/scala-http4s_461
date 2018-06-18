package com.yunjae.scalahttp4s

import cats.effect.{Effect, IO}
import io.circe.Json
import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

object HelloWorldService extends Http4sDsl[IO] {

  val service: HttpService[IO] = {
    HttpService[IO] {
      case GET -> Root /  name =>
        Ok(Json.obj("message" -> Json.fromString(s"Hello, ${name}")))
      case GET -> Root  =>
        Ok(Json.obj("message" -> Json.fromString(s"Hello world")))
      case GET -> Root / "add" / IntVar(a) / IntVar(b) =>
        Ok(Json.obj("result" -> Json.fromInt(a + b)))
    }
  }
}
