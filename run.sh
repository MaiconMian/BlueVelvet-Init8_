rm -f *.jar
mvn clean install -DskipTests
cp target/*.jar bluevelvet.jar

docker-compose build && MYSQL_DATABASE=${MYSQL_DATABASE:-mydatabase} MYSQL_PASSWORD=${MYSQL_PASSWORD:-rootpassword} docker-compose up