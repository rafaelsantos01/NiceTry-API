# Use uma base image compatível com aarch64
FROM adoptopenjdk:11-jre-hotspot-bionic-aarch64
# Copie o arquivo JAR do seu aplicativo Spring Boot para o contêiner
COPY target/*.jar app.jar
# Exponha a porta que o aplicativo Spring Boot escuta (se aplicável)
EXPOSE 8080
# Comando de inicialização do aplicativo Spring Boot
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
