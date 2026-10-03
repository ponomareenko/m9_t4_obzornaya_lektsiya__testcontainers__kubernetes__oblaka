#FROM openjdk:8-jdk-alpine
FROM eclipse-temurin:21-jre-alpine
EXPOSE 8081
ADD build/libs/conditional_prilozhenie-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]