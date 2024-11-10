Remove-Item .jar -Force
mvn clean install -DskipTests
Copy-Item target\*.jar -Destination bluevelvet.jar

docker-compose build
docker-compose up