CREATE TABLE app.user(
    uuid UUID PRIMARY KEY,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,

    fio VARCHAR(255),

    password VARCHAR(255),

    status VARCHAR(255),

    role VARCHAR(255),

    email VARCHAR(255),

    verify_code integer,

    CONSTRAINT app_user_email_key UNIQUE(email));