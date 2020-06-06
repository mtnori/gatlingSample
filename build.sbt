name := """gatlingSample"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
enablePlugins(GatlingPlugin)

scalaVersion := "2.12.10"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.2.1" % "test,it"
libraryDependencies += "io.gatling" % "gatling-test-framework" % "3.2.1" % "test,it"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
