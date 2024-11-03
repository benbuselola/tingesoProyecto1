FROM openjdk:21
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} backendBanco.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","./backendBanco.jar"]