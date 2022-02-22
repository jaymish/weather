FROM openjdk:8-jdk-alpine
COPY . /usr/src/app/
WORKDIR /usr/src/app/
EXPOSE 8081
RUN chmod +x gradlew
RUN ./gradlew clean build
RUN mv build/libs/kafka-demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
