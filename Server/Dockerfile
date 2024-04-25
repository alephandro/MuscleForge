FROM ubuntu:20.04

# Install Java 17, Maven, and Docker client
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven \
    apt-transport-https ca-certificates curl gnupg-agent software-properties-common && \
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add - && \
    add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" && \
    apt-get update && \
    apt-get install -y docker-ce

# Set working directory
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Compile the Java application using Maven
RUN mvn compile

#Create a shared folder to allocate the volume
RUN mkdir /shared-data

# Expose port 8888
EXPOSE 8888

# Execute the Java application using Maven
CMD ["mvn", "exec:java", "-Dexec.mainClass=com.example.gym.Server"]