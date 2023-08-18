CREATE DATABASE audit
    WITH
    OWNER = admin
    ENCODING = 'UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = FALSE;

\c audit;

CREATE SCHEMA app;

CREATE TABLE app.audit(
    uuid UUID PRIMARY KEY,
    dt_create timestamp without time zone NOT NULL,

    text VARCHAR(255),

    essence_type VARCHAR(255),

    id VARCHAR(255),

    user_uuid UUID,

    user_fio VARCHAR(255),

    user_role VARCHAR(255),

    user_email VARCHAR(255));