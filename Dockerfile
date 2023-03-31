FROM openjdk:8
LABEL maintainer="spring-boot-docker-example"
EXPOSE 8080
ADD target/spring-boot-aws-0.0.1-SNAPSHOT.jar spring-boot-aws-docker.jar
ENTRYPOINT ["java","-jar","spring-boot-aws-docker.jar"]