# Use the official Ubuntu Server as a base image
FROM ubuntu:latest

# Install MySQL Server without prompt for root password (for demonstration purposes)
RUN apt-get update && \
    DEBIAN_FRONTEND="noninteractive" apt-get install -y mysql-server && \
    apt-get clean

# Install cron (to schedule tasks)
RUN apt-get update && \
    apt-get -y install cron && \
    apt-get clean

RUN mkdir /nonexistent

# Copy backup script into the container
COPY backup.sh /usr/local/bin/backup.sh

# Grant execution permissions to the script
RUN chmod +x /usr/local/bin/backup.sh

# Copy cron job script into the container
COPY cronjob /etc/cron.d/backup-cron

# Apply permissions to the cron job script
RUN chmod 0644 /etc/cron.d/backup-cron

# Set the HOME environment variable for the cron user
ENV HOME /

# Activate the cron configuration
RUN crontab /etc/cron.d/backup-cron

# Start cron and MySQL services when the container launches
CMD service cron start && service mysql start && tail -f /dev/null
