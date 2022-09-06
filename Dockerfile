FROM maven:3.6.3 AS maven
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=spring-boot-api-bsf.jar

WORKDIR /opt/app

COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","spring-boot-api-bsf.jar"]