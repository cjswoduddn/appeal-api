#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=appeal-api-webservice

echo "build!"
cp $REPOSITORY/zip/*.jar $REPOSITORY

echo "what is current java process ID?"
CURRENT_PID=$(pgrep java)
echo "$CURRENT_PID"

if [ ! -z "$CURRENT_PID" ]; then
  kill -9 $CURRENT_PID
  sleep  5
fi

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)
chmod +x $JAR_NAME

nohup java -jar \
  $JAR_NAME \
  --spring.config.location=$REPOSITORY/application.yml &
