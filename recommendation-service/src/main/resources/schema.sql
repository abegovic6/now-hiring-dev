DROP SCHEMA IF EXISTS recommendation cascade;
create schema recommendation;

create table recommendation."user"
(
    id    bigint       not null
        primary key,
    uuid  VARCHAR(36) UNIQUE NOT NULL,
    email varchar(255) not null,
    name  varchar(255) not null
);

create table recommendation.job
(
    id          bigint       not null
        primary key,
    description varchar(255),
    name        varchar(255) not null,
    user_id     bigint
        constraint fkihd6m3auwpenduntl3e1opcoq
            references recommendation."user"
);

create table recommendation.recommendation
(
    id             bigint not null
        primary key,
    description    varchar(255),
    job_id         bigint not null
        constraint fkqf8hegqs70pau4c13islk39um
            references recommendation.job,
    recommended_id bigint not null
        constraint fkn66sk6q2xt3k703pblw6nmvep
            references recommendation."user",
    creator_id     bigint not null
        constraint fkk7i6pcuvmq87bvvt8rmen4jqi
            references recommendation."user"
);

create table recommendation.comment
(
    id                bigint       not null
        primary key,
    recommendation_id bigint       not null
        constraint fkk3trwohxbwn01tq0bm0dcu3l7
            references recommendation.recommendation,
    creator_id        bigint       not null
        constraint fkpv26vrgsh9237ufeforw5yfqu
            references recommendation."user",
    comment_content   varchar(255) not null
);

grant
all
privileges
on
schema
recommendation to postgres;
grant all privileges on all
tables in schema recommendation to postgres;