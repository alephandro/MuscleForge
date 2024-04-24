#!/bin/bash

BACKUP_FILE="/var/lib/mysql/backup.sql"

mysqldump -u rana -prana --MuscleForge > "$BACKUP_FILE"

echo "Backup Done"
