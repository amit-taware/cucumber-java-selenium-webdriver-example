## Run tests on local
mvn clean test

## Run test in docker container

./run-test-in-container.sh

## This project is in WIP

I am building test fw to your tests inside a docker container in parallel in AWS & Create disposable infrastructure

Following are objectives

-To bring up the Selenium Grid infrastructure on-demand

-To run our selenium tests inside a docker container

-To run multiple test suites in parallel

-To run our tests in the cloud like AWS / Azure / Google-cloud

-To build a CI + CD pipeline to automatically build our selenium project, package it as a docker image & push it into DockerHub.

I am planning to use following stack to use 

-Java
-Maven
-Cucumber
-TestNG
-RestAssured for API Module
-Appium for mobile module
-Jenkins


##Following are some important command 

docker-compose up -d --scale chrome=1 --scale firefox=2

mvn clean package -DskipTests=ture

docker-compose up -d 

cd target
java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -Dcucumber.options="classpath:features" org.testng.TestNG ../testng.xml 
java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -Dcucumber.options="classpath:features" -DBROWSER=firefox  org.testng.TestNG ../testng.xml 
java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -Dcucumber.options="classpath:features/module2" -DBROWSER=firefox  org.testng.TestNG ../module2.xml 


To create own docker image and run tests

docker build -t="cucumber/selenium-docker" .
docker run -e HUB_HOST=192.168.1.2 -e CUCUMBER_OPTIONS="classpath:features/module2" -e BROWSER=firefox -e MODULE=module2.xml  cucumber/selenium-docker
docker run -e CUCUMBER_OPTIONS="classpath:features/module2" -e BROWSER=firefox -e MODULE=module2.xml  cucumber/selenium-docker

Run in our docker image
docker run -it --entrypoint=/bin/sh cucumber/selenium-docker
 java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DHUB_HOST=192.168.1.2 -Dcucumber.options="classpath:features/module2" -DBROWSER=firefox  org.testng.TestNG module2.xml 


// create container on our image

docker run -it --entrypoint=/bin/sh cucumber/selenium-docker


//Run Jenkins 

docker run -p 8080:8080 -p 50000:50000 -v "$PWD/jenkins:/var/jenkins_home" jenkins/jenkins:lts








