FROM maven:3.9.1-eclipse-temurin-8-alpine

WORKDIR /project
COPY . .
RUN mvn clean package -DskipTests
RUN cp target/clientservice-1.0-SNAPSHOT.jar /opt/app.jar

WORKDIR /opt
RUN rm -rf /project
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
