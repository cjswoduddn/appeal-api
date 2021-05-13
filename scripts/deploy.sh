#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2

cp $REPOSITORY/zip/*.jar $REPOSITORY/

CURRENT_PID=$(pgrep java)
echo "$CURRENT_PID"

if [ ! -z "$CURRENT_PID" ]; then
  kill -9 "$CURRENT_PID"
  sleep  5
fi

# shellcheck disable=SC2012
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)
chmod +x "$JAR_NAME"

nohup java -jar "$JAR_NAME" --spring.config.location=$REPOSITORY/application.yml &

