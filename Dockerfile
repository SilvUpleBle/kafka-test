FROM openjdk:17-jdk-slim-buster
WORKDIR /app

COPY target/kafka-test.jar build/

CMD ["java", "-jar", "target/kafka-test.jar"]