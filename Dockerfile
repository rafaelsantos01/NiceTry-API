#FROM openjdk:11-jdk-slim
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]
# Use uma base image compatível com aarch64
# Use uma base image compatível com aarch64
# Use a base image compatível com aarch64

FROM openjdk:11-jre-slim
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
