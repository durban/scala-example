
updateOptions := updateOptions.value.withCachedResolution(true)

scalaVersion := "2.11.7"

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-encoding", "UTF-8",
  "-Xlint:_",
  "-Xfuture",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-dead-code",
  "-Xlog-implicits",
  //"-Ywarn-value-discard",
  "-Ywarn-unused-import"
)

libraryDependencies ++= Seq(

  "org.scalatest" %% "scalatest" % "2.2.5" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.5" % "test",
  "junit" % "junit" % "4.12" % "test"

  //"org.scalaz" %% "scalaz-core" % "7.1.3"
)
