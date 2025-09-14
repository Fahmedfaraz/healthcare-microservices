FROM openjdk:17-jdk-alpine

# Copy the JAR file into the container
COPY target/patientDemographicUI-0.0.1-SNAPSHOT.jar /app/

WORKDIR /app

# Expose port
EXPOSE 8081

# Run the app
ENTRYPOINT ["java", "-jar", "patientDemographicUI-0.0.1-SNAPSHOT.jar"]

