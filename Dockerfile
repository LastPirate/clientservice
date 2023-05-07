FROM amazoncorretto:8-alpine3.14-jre

COPY /target/clientservice-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
