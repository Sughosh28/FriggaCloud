# Use lightweight OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Create a directory for the app
WORKDIR /app

# Copy the JAR file from target folder (update name if needed)
COPY target/FriggaCloud-0.0.1-SNAPSHOT.jar

# Expose port 9091 (default Spring Boot port)
EXPOSE 9091

# Run the jar file
ENTRYPOINT ["java", "-jar", "FriggaCloud-0.0.1-SNAPSHOT.jar"]
