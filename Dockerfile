#FROM openjdk:11-jdk-slim
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]

#FROM openjdk:11-jre-slim
#COPY target/*.jar app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "/app.jar"]

FROM --platform=linux/arm64 adoptopenjdk/openjdk11:alpine
COPY target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]



#FROM ubuntu:latest AS build
#RUN apt-get update
#RUN apt-get install openjdk-17-jdk -y
#COPY . .
#RUN apt-get install maven -y
#RUN mvn clean install
#FROM openjdk:11-jdk-slim
#EXPOSE 8080
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT [ "java", "-jar", "app.jar" ]
