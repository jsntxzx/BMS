#!/bin/sh
echo "replace network connection"
sed -i "s/127.0.0.1/db/g" src/applicationContext.xml

echo 'cleaning ---------------'
docker stop db && docker rm db
docker stop app && docker rm app
docker rmi tomcat:BMS

echo "packaging ------------"
mvn package 
docker build -t tomcat:BMS -f conf/DockerApp .

echo "deploying ------------"
docker run --name db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root  -d mysql
until `docker exec -i db mysql -uroot -proot < conf/bms.sql`
do 
     sleep 3
done
docker run --name app --link db:db -p 8080:8080 -d tomcat:BMS
