FROM openjdk:8-alpine
ADD target/app.jar Packers-Movers.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "Packers-Movers.jar"]