# Stage 1: Build the application
FROM gradle:7.3.1-jdk21 AS build
WORKDIR /app
COPY . /app
RUN gradle clean build -x test

# Stage 2: Run the application
FROM openjdk:21-jre-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]