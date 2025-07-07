# Use lightweight OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Create a directory for the app
WORKDIR /app

# ✅ FIX: Copy JAR to current directory as app.jar
COPY target/FriggaCloud-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot port (change if needed)
EXPOSE 9091

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
