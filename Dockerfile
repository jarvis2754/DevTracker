# Use Maven image to build the project
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first (better caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and build the jar
COPY src ./src
RUN mvn clean package -DskipTests

# Use smaller JDK image for runtime
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/DevTracker-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
