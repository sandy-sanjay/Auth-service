# =========================
# 1️⃣ BUILD STAGE
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom and source
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests


# =========================
# 2️⃣ RUNTIME STAGE
# =========================
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8081

# Run app
ENTRYPOINT ["java","-jar","app.jar"]
