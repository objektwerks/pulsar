name := "pulsar"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.11"
libraryDependencies ++= {
  val pulsar4sVersion = "2.7.3"
  Seq(
    "com.sksamuel.pulsar4s" %% "pulsar4s-core" % pulsar4sVersion,
    "com.sksamuel.pulsar4s" %% "pulsar4s-circe" % pulsar4sVersion,
    "com.typesafe" % "config" % "1.4.2",
    "ch.qos.logback" % "logback-classic" % "1.4.11",
    "org.scalatest" %% "scalatest" % "3.2.16" % Test
  )
}
