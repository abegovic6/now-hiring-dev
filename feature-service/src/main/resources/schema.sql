-- drop and create schema
DROP SCHEMA IF EXISTS aeesfeature cascade;
create schema aeesfeature;


CREATE TABLE aeesfeature.user
(
    id              SERIAL      PRIMARY KEY,
    email           VARCHAR(255) NOT NULL,
    uuid         VARCHAR(255) NOT NULL
            UNIQUE
);

-- create tables
CREATE TABLE aeesfeature.example
(
    id   SERIAL PRIMARY KEY,
    text VARCHAR(50)
);

CREATE TABLE aeesfeature.education
(
    id              SERIAL      PRIMARY KEY,
    description     VARCHAR(255) NOT NULL,
    title           VARCHAR(255) NOT NULL,
    starting_month  INT         NOT NULL,
    end_month       INT         NOT NULL,
    starting_year   INT         NOT NULL,
    end_year        INT         NOT NULL,
    user_id         INT         NOT NULL
            UNIQUE,
    FOREIGN KEY (user_id)
        REFERENCES aeesfeature.user (id)
);

CREATE TABLE aeesfeature.experience
(
    id              SERIAL      PRIMARY KEY,
    description     VARCHAR(255) NOT NULL,
    title           VARCHAR(255) NOT NULL,
    starting_month  INT         NOT NULL,
    end_month       INT         NOT NULL,
    starting_year   INT         NOT NULL,
    end_year        INT         NOT NULL,
    user_id         INT         NOT NULL
            UNIQUE,
    FOREIGN KEY (user_id)
        REFERENCES aeesfeature.user (id)
);

CREATE TABLE aeesfeature.skill
(
    id              SERIAL      PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    user_id         INT         NOT NULL
            UNIQUE,
    FOREIGN KEY (user_id)
        REFERENCES aeesfeature.user (id)
);


CREATE TABLE aeesfeature.files
(
    id              SERIAL      PRIMARY KEY,
    data           VARCHAR(255) NOT NULL,
    name           VARCHAR(255) NOT NULL,
    type           VARCHAR(255) NOT NULL
);

-- grant all access to user postgres
grant
all
privileges
on
schema
aeesfeature to postgres;
grant all privileges on all
tables in schema aeesfeature to postgres;