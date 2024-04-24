#!/bin/bash

BACKUP_FILE="/shared-data/backup.sql"

mysqldump -u rana -prana --MuscleForge > "$BACKUP_FILE"

echo "Backup Done"
