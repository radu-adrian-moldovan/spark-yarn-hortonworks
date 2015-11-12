#!/usr/bin/env bash

mvn clean install -DskipTests

scp -P 2222 ./target/Spark-Yarn-Hortonworks-1.4.1.2.3.2.0-2950.jar hdfs@127.0.0.1:

# make sure you have passwordless ssh
ssh hdfs@127.0.0.1 -p 2222 "spark-submit --class ram.scala.Sample --master yarn-cluster --num-executors 1 --driver-memory 512m --executor-memory 512m --executor-cores 1 Spark-Yarn-Hortonworks-1.4.1.2.3.2.0-2950.jar"
