FROM openjdk:17

WORKDIR '/my-app'

RUN mvn clean package

RUN mkdir 'myfiles'

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
