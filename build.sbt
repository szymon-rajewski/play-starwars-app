name := """unide-java-task"""
organization := "it.unide"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.9"

libraryDependencies += guice

libraryDependencies += ws

val jacksonDatatype = "2.11.1"
val vavr = "0.10.4"

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % jacksonDatatype
libraryDependencies += "com.fasterxml.jackson.datatype" % "jackson-datatype-hibernate5" % jacksonDatatype
libraryDependencies += "com.fasterxml.jackson.dataformat" % "jackson-dataformat-xml" % jacksonDatatype
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % jacksonDatatype
libraryDependencies += "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % jacksonDatatype
libraryDependencies += "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % jacksonDatatype
libraryDependencies += "io.vavr" % "vavr" % vavr