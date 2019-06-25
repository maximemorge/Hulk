name := "Hulk"

version := "1.0"

scalaVersion := "2.12.8"

lazy val akkaVersion = "2.6.0-M3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion
)
