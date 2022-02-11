FROM docker.io/openjdk:11
EXPOSE 8080
COPY build/libs/demo-0.1-all.jar /server.jar
COPY . .
RUN ./gradlew build
ENTRYPOINT java -jar build/libs/demo-0.1-all.jar
