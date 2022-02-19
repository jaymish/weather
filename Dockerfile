FROM openjdk:8-jdk-alpine
COPY . /usr/src/app/
WORKDIR /usr/src/app/
EXPOSE 8080
RUN ./gradlew clean build
RUN mv build/libs/demo-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/weather.jar"]