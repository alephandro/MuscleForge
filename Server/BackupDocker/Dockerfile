FROM debian:latest

# Install prerequisites
RUN apt-get update && \
    apt-get install -y gnupg2 wget lsb-release && \
    rm -rf /var/lib/apt/lists/*

# Add MySQL APT repository GPG key
RUN wget -O /tmp/RPM-GPG-KEY-mysql https://repo.mysql.com/RPM-GPG-KEY-mysql && \
    apt-key add /tmp/RPM-GPG-KEY-mysql && \
    rm /tmp/RPM-GPG-KEY-mysql

# Add MySQL APT repository
RUN wget -O /tmp/mysql-apt-config.deb https://dev.mysql.com/get/mysql-apt-config_0.8.22-1_all.deb && \
    dpkg -i /tmp/mysql-apt-config.deb && \
    rm /tmp/mysql-apt-config.deb

# Install MySQL Server
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y mysql-server && \
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
