FROM openjdk:8u121-jre-alpine
ADD target/rabbitmq-amqp-1.0.0.jar rabbitmq-amqp-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /rabbitmq-amqp-1.0.0.jar"]
