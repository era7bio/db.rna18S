Nice.scalaProject

name          := "db.rna18S"
organization  := "era7bio"
description   := "db.rna18S project"
// the repo name differs on github:
GithubRelease.repo := s"${organization.value}/db.rna18S"

scalaVersion := "2.11.8"


resolvers :=
  ("Era7 private maven releases" at s3("private.releases.era7.com").toHttps(s3region.value.toString) ) +: resolvers.value


libraryDependencies ++= Seq(
  "ohnosequences" %% "fastarious"      % "0.6.0",
  "ohnosequences" %% "blast-api"       % "0.7.0",
  "ohnosequences" %% "statika"         % "2.0.0-M5",
  "era7bio"       %% "rnacentraldb"    % "0.2.1",
  "ohnosequences-bundles" %% "bio4j-dist" % "0.2.0",
  // Test:
  "era7"          %% "defaults"  % "0.1.0" % Test,
  "org.scalatest" %% "scalatest" % "2.2.6" % Test
)

dependencyOverrides ++= Set(
  "org.apache.httpcomponents"  % "httpclient"          % "4.5.1",
  "org.slf4j"                  % "slf4j-api"           % "1.7.7"
)


bucketSuffix := "era7.com"

fatArtifactSettings

// copied from bio4j-titan:
mergeStrategy in assembly ~= { old => {
    case "log4j.properties"                       => MergeStrategy.filterDistinctLines
    case PathList("org", "apache", "commons", _*) => MergeStrategy.first
    case x                                        => old(x)
  }
}

enablePlugins(BuildInfoPlugin)
buildInfoPackage := "generated.metadata"
buildInfoObject  := name.value.split("""\W""").map(_.capitalize).mkString
buildInfoOptions := Seq(BuildInfoOption.Traits("ohnosequences.statika.AnyArtifactMetadata"))
buildInfoKeys    := Seq[BuildInfoKey](
  organization,
  version,
  "artifact" -> name.value.toLowerCase,
  "artifactUrl" -> fatArtifactUrl.value
)

// For including test code in the fat artifact:
unmanagedSourceDirectories in Compile += (scalaSource in Test).value / "compats.scala"
