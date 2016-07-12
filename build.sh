#!/bin/sh

mvn package
rm -rf /opt/tomcat/webapps/*
cp target/BMS.war /opt/tomcat/webapps/
catalina.sh stop
catalina.sh start
