FROM openjdk:8-jdk-alpine

VOLUME /tmp
COPY ./build/libs/app.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]