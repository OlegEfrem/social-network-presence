import Dependencies.versions

val root = (project in file("."))
  .settings(
    name := "social-network-presence",
    version := "0.1.0",
    organization := "com.db",
    scalaVersion := "2.13.1",
    libraryDependencies ++= libraries,
    scalacOptions := compilerFlags,
    Defaults.itSettings
  )
  .settings(
    // We need some test classes in it:test
    compile in IntegrationTest := (compile in IntegrationTest).dependsOn(compile in Test).value,
    internalDependencyClasspath in IntegrationTest += Attributed.blank((classDirectory in Test).value)
  )
  .configs(IntegrationTest extend Test)

lazy val libraries = Seq(
  "org.scalatest" %% "scalatest" % versions.scalaTest % Test,
  "com.beachape" %% "enumeratum" % versions.enumeratum
)

// http://tpolecat.github.io/2017/04/25/scalac-flags.html
lazy val compilerFlags = Seq(
  "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",                // Specify character encoding used by source files.
  "-explaintypes",                     // Explain type errors in more detail.
  "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
  "-language:existentials",            // Existential types (besides wildcard types) can be written and inferred
  "-language:experimental.macros",     // Allow macro definition (besides implementation and application)
  "-language:higherKinds",             // Allow higher-kinded types
  "-language:implicitConversions",     // Allow definition of implicit functions called views
  "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
  "-Ywarn-value-discard",              // Warn when non-Unit expression results are unused.
  "-language:postfixOps"
)


javaOptions := Seq(
  "-DENCODER=HUMAN"
)

// test:console is unusable with these flags, so we omit them when using the REPL:
scalacOptions in console --= Seq("-Ywarn-unused:imports", "-Xfatal-warnings")

enablePlugins(JavaAppPackaging)

// run scalaStyle on compile:
lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")

compileScalastyle := scalastyle.in(Compile).toTask("").value

(compile in Compile) := ((compile in Compile) dependsOn compileScalastyle).value

// run scalaStyle on test sources:
lazy val testScalastyle = taskKey[Unit]("testScalastyle")

testScalastyle := scalastyle.in(Test).toTask("").value

(test in Test) := ((test in Test) dependsOn testScalastyle).value

// sbt-updates configurations: see => https://github.com/rtimush/sbt-updates
dependencyUpdatesFailBuild := true

Revolver.settings
