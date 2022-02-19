FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
RUN ./gradlew clean build
RUN mv build/libs/demo-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/weather.jar"]