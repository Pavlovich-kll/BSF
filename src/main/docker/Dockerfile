FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
#COPY pom.xml /BSF
ENTRYPOINT ["java", "-jar", "application.jar"]