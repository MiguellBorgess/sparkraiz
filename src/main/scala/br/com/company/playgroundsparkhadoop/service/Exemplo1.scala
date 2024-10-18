package br.com.company.playgroundsparkhadoop.service

import br.com.company.playgroundsparkhadoop.service.Exemplo1.Pessoa
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

import scala.util.Random

object Exemplo1 {

  case class Pessoa(id: Int, nome: String, idade: Int, salario: Double)

  def main(args : Array[String]): Unit = {

    val spark : SparkSession = SparkSession
      .builder()
      .appName(s"play-ground-spark-hadoop")
      .master("local[*]")
//      .config("hive.exec.dynamic.partition.mode","nonstrict")
//      .config("hive.exec.dynamic.partition","true")
//      .enableHiveSupport()
      .getOrCreate()

    val applicationId : String = spark.sparkContext.applicationId
    val applicationName : String = spark.sparkContext.appName

    println(s"**********************************************************************************")
    println(s"*** Application ID: $applicationId ")
    println(s"*** Application Name: $applicationName ")
    println(s"**********************************************************************************")

    spark.sparkContext.setLogLevel("ERROR")

    // Gerando os dados aleatórios
    val dados = gerarDados(1000)

    // Convertendo para DataFrame
    import spark.implicits._
    val df = spark.createDataFrame(dados)

    // Escrevendo os dados no formato Parquet
    df.write
      .mode("overwrite") // Sobrescreve se o arquivo já existir
      .parquet("c:\\users\\vsevaios\\playground-spark\\data\\pessoas")

    // Encerrando a sessão Spark
    spark.stop()
  }

  // Função para gerar dados aleatórios
  def gerarDados(qtd: Int): Seq[Pessoa] = {
    val random = new Random()
    (1 to qtd).map { id =>
      val nome = s"Pessoa_$id"
      val idade = random.nextInt(60) + 18 // Idade entre 18 e 77 anos
      val salario = random.nextDouble() * 10000 // Salário até 10.000
      Pessoa(id, nome, idade, salario)
    }
  }
}