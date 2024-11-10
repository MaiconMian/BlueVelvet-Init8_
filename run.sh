rm -f *.jar
mvn clean install -DskipTests
cp target/*.jar bluevelvet.jar

docker-compose build && docker-compose up