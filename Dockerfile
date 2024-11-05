FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/ms-user-0.0.1-SNAPSHOT.jar /app/ms-user.jar
EXPOSE 8181
LABEL authors="Fuad Muradov"
ENTRYPOINT ["java", "-jar", "/app/ms-user.jar"]