FROM openjdk:17-jdk-alpine

# Copy the JAR file into the container
COPY target/com.mediscreen.patient-demographics-0.0.1-SNAPSHOT.jar /app/

WORKDIR /app

# Expose port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "com.mediscreen.patient-demographics-0.0.1-SNAPSHOT.jar"]