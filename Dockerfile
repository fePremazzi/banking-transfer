FROM maven:3.6.3-jdk-14 as MAVEN_BUILD

COPY ./ ./

RUN mvn clean package

FROM openjdk:14.0.2-slim-buster

COPY --from=MAVEN_BUILD /target/banking-transfer-0.0.1-SNAPSHOT.jar /scheduler.jar

CMD [ "java", "-jar", "/scheduler.jar" ]