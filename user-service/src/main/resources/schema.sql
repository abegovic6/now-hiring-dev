-- drop and create schema
drop schema aeesuser cascade;
create schema aeesuser;


-- create tables
CREATE TABLE aeesuser.example
(
    id   SERIAL PRIMARY KEY,
    text VARCHAR(50)
);

CREATE TABLE aeesuser.location
(
    id              SERIAL PRIMARY KEY,
    city            VARCHAR(50) NOT NULL,
    country         VARCHAR(50) NOT NULL,
    creation_ts     TIMESTAMP   NOT NULL,
    modification_ts TIMESTAMP
);

create table aeesuser.user
(
    id              SERIAL PRIMARY KEY,
    location_id     INT                NOT NULL,

    uuid            VARCHAR(36) UNIQUE NOT NULL,
    email           VARCHAR(50) UNIQUE NOT NULL,
    password        VARCHAR(100)       NOT NULL,
    user_type       VARCHAR(50)        NOT NULL,

    company_name    VARCHAR(50),
    first_name      VARCHAR(50),
    last_name       VARCHAR(50),
    description     VARCHAR(255),

    creation_ts     TIMESTAMP          NOT NULL,
    modification_ts TIMESTAMP,

    FOREIGN KEY (location_id)
        REFERENCES aeesuser.location (id)
);

CREATE TABLE aeesuser.connection
(
    id              SERIAL PRIMARY KEY,
    user_from       INT         NOT NULL,
    user_to         INT         NOT NULL,
    creation_ts     TIMESTAMP   NOT NULL,
    status          VARCHAR(50) NOT NULL,
    modification_ts TIMESTAMP,
    FOREIGN KEY (user_from)
        REFERENCES aeesuser.user (id),
    FOREIGN KEY (user_to)
        REFERENCES aeesuser.user (id)

);

CREATE TABLE aeesuser.notification
(
    id                SERIAL PRIMARY KEY,

    from_user         INT         NOT NULL,
    recipient_user    INT         NOT NULL,

    creation_ts       TIMESTAMP   NOT NULL,
    notification_type VARCHAR(50) NOT NULL,

    text              VARCHAR(255),

    viewed            BOOLEAN     NOT NULL DEFAULT FALSE,
    FOREIGN KEY (from_user)
        REFERENCES aeesuser.user (id),
    FOREIGN KEY (recipient_user)
        REFERENCES aeesuser.user (id)

);


-- grant all access to user postgres
grant
all
privileges
on
schema
aeesuser to postgres;
grant all privileges on all
tables in schema aeesuser to postgres;

