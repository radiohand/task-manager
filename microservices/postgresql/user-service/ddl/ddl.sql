CREATE DATABASE "user"
    WITH
    OWNER = admin
    ENCODING = 'UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = FALSE;

\c user;

CREATE SCHEMA app;

CREATE TABLE app.user(
    uuid UUID PRIMARY KEY,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,

    fio VARCHAR(255),

    password VARCHAR(255),

    status VARCHAR(255),

    role VARCHAR(255),

    email VARCHAR(255),

    CONSTRAINT app_user_email_key UNIQUE(email));

CREATE TABLE app.code(
    uuid UUID PRIMARY KEY,
    user_uuid UUID,
    code integer,
    FOREIGN KEY (user_uuid) REFERENCES app.user(uuid));