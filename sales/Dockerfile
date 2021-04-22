FROM maven:3.6-jdk-11
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package
CMD java -jar target/sales-0.0.1-SNAPSHOT.jar

