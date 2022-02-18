FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD build/libs/weather1-0.0.1-SNAPSHOT.jar weather.jar
ENTRYPOINT ["java","-jar","/weather.jar"]