# Use the official Ubuntu 20.04 LTS image
FROM ubuntu:20.04

# Set environment variables for MySQL installation
ENV MYSQL_ROOT_PASSWORD=rootpassword
ENV DEBIAN_FRONTEND=noninteractive

# Install MySQL server and cron without prompt
RUN apt-get update && \
    apt-get install -y mysql-server cron && \
    rm -rf /var/lib/apt/lists/*

# Expose the MySQL port
EXPOSE 3306

# Copy the backup script into the container
COPY backup.sh /usr/local/bin/backup.sh

RUN mkdir /shared-data

# Grant execute permissions to the script
RUN chmod +x /usr/local/bin/backup.sh

# Copy the cron job script into the container
COPY cronjob /etc/cron.d/backup-cron

# Set permissions for the cron job script
RUN chmod 0644 /etc/cron.d/backup-cron

# Apply cron job configuration
RUN crontab /etc/cron.d/backup-cron

# Start MySQL service and cron when the container starts
CMD ["sh", "-c", "service mysql start && cron -f"]
