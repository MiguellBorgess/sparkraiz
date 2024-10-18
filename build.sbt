name := "PlayGroundSparkHadoop"
version := "0.1"
scalaVersion := "2.13.14"

val sparkVersion = "3.5.2"
val hadoopVersion = "3.3.6"
val sftpVersion = "1.1.3"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core"    % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql"     % sparkVersion % "provided",
  "org.apache.spark" %% "spark-hive"    % sparkVersion % "provided",
  "com.springml" % "spark-sftp_2.11"    % sftpVersion,
  "org.apache.hadoop" % "hadoop-common" % hadoopVersion % "provided"
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
  "spark-sql" at "https://mvnrepository.com/artifact/org.apache.spark/spark-sql",
  "spark-sftp" at "https://mvnrepository.com/artifact/com.springml/spark-sftp"
)