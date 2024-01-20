FROM 3.9.4-eclipse-temurin-11-alpine AS build-app

WORKDIR '/my-app'

RUN mkdir 'myfiles'

RUN mvn clean package


FROM openjdk:17 AS copy_build

ARG JAR_FILE=target/*.jar
COPY --from=build-app ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
