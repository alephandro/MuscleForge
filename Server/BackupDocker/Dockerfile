FROM ubuntu:latest

# Install MySQL server
RUN apt-get update && \
    apt-get install -y mysql-server && \
    rm -rf /var/lib/apt/lists/*

# Install cron
RUN apt-get update && \
    apt-get install -y cron && \
    rm -rf /var/lib/apt/lists/*

# Copy the backup script into the container
COPY backup.sh /usr/local/bin/backup.sh

# Grant execute permissions to the script
RUN chmod +x /usr/local/bin/backup.sh

# Copy the cron job script into the container
COPY cronjob /etc/cron.d/backup-cron

# Set permissions for the cron job script
RUN chmod 0644 /etc/cron.d/backup-cron

# Apply cron job configuration
RUN crontab /etc/cron.d/backup-cron

# Start MySQL service on container startup
CMD ["mysqld", "--daemonize"]
