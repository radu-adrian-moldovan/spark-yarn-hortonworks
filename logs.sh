#!/usr/bin/env bash

echo 'Application ID <provided by Yarn res. mgmt e.g. application_1447159372353_0009/2>: ' ${1}

ssh hdfs@127.0.0.1 -p 2222 "yarn logs -applicationId ${1} > /tmp/${1}.log"

ssh hdfs@127.0.0.1 -p 2222 "yarn logs -applicationId ${1}| grep '#RAM#' > /tmp/RAM_${1}.log"

scp -P 2222 hdfs@127.0.0.1:/tmp/$1.log ./logs

scp -P 2222 hdfs@127.0.0.1:/tmp/RAM_$1.log ./logs

ssh hdfs@127.0.0.1 -p 2222 "yarn logs -applicationId ${1} | grep '#RAM#'"