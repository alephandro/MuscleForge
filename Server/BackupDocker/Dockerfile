# Use the official MySQL image from Docker Hub
FROM mysql:8.0

# Install cron package
RUN apt-get update && apt-get install -y cron

# Environment variables
ENV MYSQL_ROOT_PASSWORD=rootpassword

# Expose the MySQL port
EXPOSE 3306

# Start MySQL service on container startup
CMD ["mysqld"]
