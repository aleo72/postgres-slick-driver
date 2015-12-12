name := """postgres-slick-driver"""

scalaVersion := "2.11.7"
organization := "com.blueskiron"
version in ThisBuild := "0.0.1"

libraryDependencies ++= List(
  "com.github.tminglei" %% "slick-pg" % "0.10.2",
  "com.github.tminglei" %% "slick-pg_core" % "0.10.2",
  "com.github.tminglei" %% "slick-pg_play-json" % "0.10.2",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "com.h2database" % "h2" % "1.3.175" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)
