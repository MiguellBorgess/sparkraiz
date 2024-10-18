@echo off
REM Defina o diretório onde o Spark está instalado
set SPARK_HOME=C:\spark\spark-3.5.2-bin-hadoop3-scala2.13

REM Defina o caminho para o script ou arquivo jar que deseja executar
set APP_PATH=PlayGroundSparkHadoop-assembly-0.1.jar

REM Defina a classe principal do seu job Scala
set MAIN_CLASS=br.com.company.playgroundsparkhadoop.service.Exemplo1

REM Defina outros parâmetros, como o número de executores, memória, etc.
set MASTER=local[*]
set DRIVER_MEMORY=2G
set EXECUTOR_MEMORY=2G

REM Caminho para o arquivo de logs, se necessário
set LOG_PATH=C:\users\vsevaios\playground-spark\log.txt

REM Execute o spark-submit
%SPARK_HOME%\bin\spark-submit ^
--class %MAIN_CLASS% ^
--master %MASTER% ^
--driver-memory %DRIVER_MEMORY% ^
--executor-memory %EXECUTOR_MEMORY% ^
%APP_PATH% > %LOG_PATH% 2>&1

REM Mensagem de conclusão
echo Spark job executado! Verifique os logs em %LOG_PATH%
pause