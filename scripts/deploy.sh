#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=springboot-project

echo "> Build file copy"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> Current running Application pid Check"

CURRENT_PID=$(pgrep -fl springboot-project | grep jar | awk '{print $1}')

echo "Current running Application pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ];
        then echo "> Current Application not exist So No Finish"
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> new Application deploy"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR NAME: $JAR_NAME"

echo "> $JAR_NAME exe add"
chmod +x $JAR_NAME

echo "> $JAR_NAME run"

nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=real \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &


