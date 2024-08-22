FROM mysql:8.0

COPY 01_create_db.sql /docker-entrypoint-initdb.d/01_create_db.sql

ENV MYSQL_ROOT_PASSWORD=root

VOLUME ./db_data:/var/lib/mysql