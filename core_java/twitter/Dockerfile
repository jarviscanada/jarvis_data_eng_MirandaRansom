FROM openjdk:8-alpine
COPY target/twitter*.jar /usr/local/app/twitter/twitter.jar
ENTRYPOINT ["java", "-jar", "/usr/local/app/twitter/twitter.jar"]