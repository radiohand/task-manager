CREATE TABLE app.user(
    uuid UUID PRIMARY KEY);

CREATE TABLE app.task(
    uuid UUID PRIMARY KEY,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,

    title VARCHAR(255),

    description VARCHAR(255),

    task_status VARCHAR(255),

    implementer_uuid UUID,

    project_uuid UUID);

CREATE TABLE app.project(
    uuid UUID PRIMARY KEY,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,

    name VARCHAR(255),

    description VARCHAR(255),

    manager_uuid UUID,

    project_status VARCHAR(255));

CREATE TABLE app.project_staff(
    project_uuid UUID,
    user_uuid UUID,
    FOREIGN KEY (project_uuid) REFERENCES app.project(uuid),
    FOREIGN KEY (user_uuid) REFERENCES app.user(uuid))