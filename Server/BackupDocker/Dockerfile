FROM mysql:latest

# Instalar el paquete cron
RUN apt-get update && apt-get install -y cron

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

# Ejecutar cron en primer plano
CMD ["cron", "-f"]
