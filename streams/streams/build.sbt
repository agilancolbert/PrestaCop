

scalaVersion := "2.11.8"

name := "hello-world"
organization := "ch.epfl.scala"
version := "1.0"

libraryDependencies += "org.apache.kafka" %% "kafka" % "2.3.0"
libraryDependencies += "log4j" % "log4j" % "1.2.17"
libraryDependencies += "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.12.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.12.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.12.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.4"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.4"




