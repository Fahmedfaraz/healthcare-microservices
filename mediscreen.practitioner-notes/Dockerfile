FROM openjdk:17-jdk-alpine

# Copy the JAR file into the container
COPY target/mediscreen.practitioner-notes-0.0.1-SNAPSHOT.jar /app/

WORKDIR /app

# Expose port
EXPOSE 9095

# Run the app
ENTRYPOINT ["java", "-jar", "mediscreen.practitioner-notes-0.0.1-SNAPSHOT.jar"]

