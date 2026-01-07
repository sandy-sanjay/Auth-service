# Java 17 runtime
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy the built JAR into container
COPY target/*.jar app.jar

# Expose Auth service port
EXPOSE 8081

# Run Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
