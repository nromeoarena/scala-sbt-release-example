import sbtrelease._

name := "scala-sbt-release-example"

organization := "com.cgnal"

scalaVersion := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= {
  val scalaTestV          = "2.2.6"

  Seq(
    "org.scalatest" %% "scalatest" % scalaTestV % "test"
  )
}

enablePlugins(GitVersioning)

// strip the qualifier off the input version, eg. 1.2.1-SNAPSHOT -> 1.2.1
releaseVersion     := { ver => Version(ver).map(_.withoutQualifier.string).getOrElse(versionFormatError) }

// bump the minor version and append '-SNAPSHOT', eg. 1.2.1 -> 1.3.0-SNAPSHOT
releaseNextVersion := { ver => Version(ver).map(_.bumpMinor.asSnapshot.string).getOrElse(versionFormatError) }