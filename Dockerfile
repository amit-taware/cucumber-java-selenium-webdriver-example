FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

# Workspace
WORKDIR /usr/share/udemy

# ADD .jar under target from host
# into this image
ADD target/selenium-docker.jar 			selenium-docker.jar
ADD target/selenium-docker-tests.jar 	selenium-docker-tests.jar
ADD target/libs							libs

# in case of any other dependency like .csv / .json / .xls
# please ADD that as well

# ADD suite files
ADD module1.xml                          module1.xml
ADD module2.xml                          module2.xml


# ADD health check script
ADD healthcheck.sh                      healthcheck.sh

# BROWSER
# HUB_HOST
# MODULE

ENTRYPOINT sh healthcheck.sh

#ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -Dcucumber.options="$CUCUMBER_OPTIONS" -DBROWSER=$BROWSER  -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE

#classpath:features/module2