#!/usr/bin/env bash
set -e

export UNIQUE_BUILD_ID=${UNIQUE_BUILD_ID:-localcuke}

docker-compose -f docker-compose.yaml -p ${UNIQUE_BUILD_ID} down

mvn clean package -DskipTests

#docker-compose -f docker-compose.yaml -p ${UNIQUE_BUILD_ID} build module1

docker build -t="cucumber/selenium-docker" .

docker-compose -f docker-compose.yaml -p ${UNIQUE_BUILD_ID} up --scale chrome=1 --scale firefox=2 --abort-on-container-exit --no-color

docker-compose -f docker-compose.yaml -p ${UNIQUE_BUILD_ID} down


