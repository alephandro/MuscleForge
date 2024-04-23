# Use the official Maven image as base
FROM maven:3.8.4-openjdk-17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Compile the Maven project
RUN mvn compile

# Expose port 8888
EXPOSE 8888

# Run the application
CMD ["mvn", "exec:java", "-Dexec.mainClass=com.example.gym.Server"]