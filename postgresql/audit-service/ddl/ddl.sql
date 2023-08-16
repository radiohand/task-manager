CREATE TABLE app.audit(
    uuid UUID PRIMARY KEY,
    dt_create timestamp without time zone NOT NULL,

    text VARCHAR(255),

    essence_type VARCHAR(255),

    id VARCHAR(255),

    user_uuid UUID,

    user_fio VARCHAR(255),

    user_role VARCHAR(255),

    user_email VARCHAR(255))