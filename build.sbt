name := "PlayGroundSparkHadoop"
version := "0.1"
scalaVersion := "2.13.14"

val sparkVersion = "3.5.5"
val hadoopVersion = "3.3.6"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core"    % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql"     % sparkVersion % "provided",
  "org.apache.spark" %% "spark-hive"    % sparkVersion % "provided",
  "org.apache.hadoop" % "hadoop-common" % hadoopVersion % "provided",
  "org.scala-lang" % "scala-library" % "2.13.16"
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) =>
    xs map {_.toLowerCase} match {
      case "manifest.mf" :: Nil | "index.list" :: Nil | "dependencies" :: Nil =>
        MergeStrategy.discard
      case ps @ x :: xs if ps.last.endsWith(".sf") || ps.last.endsWith(".dsa") =>
        MergeStrategy.discard
      case "services" :: _ =>  MergeStrategy.filterDistinctLines
      case _ => MergeStrategy.first
    }
  case _ => MergeStrategy.first
}

resolvers ++= Seq(
  "spark-core" at "https://mvnrepository.com/artifact/org.apache.spark/spark-core",
  "spark-sql" at "https://mvnrepository.com/artifact/org.apache.spark/spark-sql"
)