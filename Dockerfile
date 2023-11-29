# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine as build-stage

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle configuration files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Grant execution permissions on the Gradle wrapper
RUN chmod +x ./gradlew

# Copy the project source
COPY src src

# Build the application
RUN ./gradlew build -x test

# Start a new build stage and set the base image for running the app
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory in the image
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build-stage /app/build/libs/*.jar app.jar

# Make port 8081 available to the world outside this container
EXPOSE 8081

# Run the JAR file
ENTRYPOINT ["java","-jar","app.jar"]
