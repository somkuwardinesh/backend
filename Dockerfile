FROM maven:3.9.4 AS build-app

WORKDIR '/my-app'

RUN mkdir 'myfiles'

COPY . /my-app

RUN mvn clean package


FROM openjdk:17 AS copy_build


ARG JAR_FILE=/my-app/target/*.jar

COPY --from=build-app  ${JAR_FILE}  app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
