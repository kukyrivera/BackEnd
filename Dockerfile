FROM amazoncorretto:11-alpine-jdk
MAINTAINER Luciano
COPY target/lucianoRivera-0.0.1-SNAPSHOT.jar backpf.jar
ENTRYPOINT ["java","-jar","/backpf.jar"]