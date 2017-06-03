import Dependencies._
import ReleaseTransformations._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.github.hayasshi",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "akkahttp_easyrouter",
    scalacOptions ++= {
      Seq("-unchecked", "-deprecation", "-feature", "-language:implicitConversions", "-language:higherKinds", "-Xfuture", "-Xlint")
    },
    libraryDependencies ++= Seq(
      akkaHttp % Provided,
      akkaHttpTestKit % Test,
      scalaTest % Test
    ),
    pomExtra in Global := {
      <url>https://github.com/hayasshi/akka-http-easy-router</url>
      <licenses>
        <license>
          <name>Apache 2</name>
          <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
        </license>
      </licenses>
      <scm>
        <connection>scm:git:github.com/hayasshi/akka-http-easy-router</connection>
        <developerConnection>scm:git:git@github.com:hayasshi/akka-http-easy-router</developerConnection>
        <url>github.com/hayasshi/akka-http-easy-router</url>
      </scm>
      <developers>
        <developer>
          <id>hayasshi</id>
          <name>Daisuke Hayashi</name>
          <url>https://github.com/hayasshi</url>
        </developer>
      </developers>
    },
    credentials += Credentials(
      "Sonatype Nexus Repository Manager",
      "oss.sonatype.org",
      sys.env.getOrElse("SONATYPE_USER", ""),
      sys.env.getOrElse("SONATYPE_PASSWORD", "")
    ),
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      ReleaseStep(action = Command.process("publishSigned", _)),
      setNextVersion,
      commitNextVersion,
      ReleaseStep(action = Command.process("sonatypeReleaseAll", _)),
      pushChanges
    )
  )
