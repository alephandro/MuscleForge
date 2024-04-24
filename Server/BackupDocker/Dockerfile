FROM debian:latest

# Install MySQL
RUN apt-get update && \
    apt-get install -y mysql-server && \
    rm -rf /var/lib/apt/lists/*

# Instalar cron
RUN apt-get update && apt-get install -y cron && rm -rf /var/lib/apt/lists/*

# Copiar el script de respaldo al contenedor
COPY backup.sh /usr/local/bin/backup.sh

# Dar permisos de ejecución al script
RUN chmod +x /usr/local/bin/backup.sh

# Copiar el script de cron al contenedor
COPY cronjob /etc/cron.d/backup-cron

# Aplicar permisos al script de cron
RUN chmod 0644 /etc/cron.d/backup-cron

# Activar la configuración de cron
RUN crontab /etc/cron.d/backup-cron

# Iniciar el servicio de cron al iniciar el contenedor
CMD service mysql start && cron -f
