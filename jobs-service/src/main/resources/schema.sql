-- drop and create schema

DROP SCHEMA IF EXISTS aeesjobs cascade;
create schema aeesjobs;

-- create tables

create table aeesjobs."user"
(
    id           serial
        primary key,
    company_name varchar(255),
    description  varchar(255),
    e_mail       varchar(255) not null
            unique,
    first_name   varchar(255),
    last_name    varchar(255),
    location     varchar(255),
    uid          varchar(255) not null
            unique,
    user_type    varchar(255) not null
);

create table aeesjobs.job
(
    id          serial
        primary key,
    company_id  varchar(255),
    description varchar(500),
    location    varchar(255),
    title       varchar(255),
    type        varchar(255),
    valid       date
);

create table aeesjobs.application
(
    id          serial
        primary key,
    coverletter varchar(255),
    useruid     varchar(255)
);

create table aeesjobs.job2application
(
    id            serial
        primary key,
    applicationid integer,
    jobid         integer
);

-- grant all access to user postgres
grant
all
privileges
on
schema
aeesjobs to postgres;
grant all privileges on all
tables in schema aeesjobs to postgres;