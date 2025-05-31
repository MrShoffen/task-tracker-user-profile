--liquibase formatted sql


--changeset mrshoffen:2
CREATE TABLE IF NOT EXISTS users
(
    id              UUID PRIMARY KEY,
    email           VARCHAR(255) NOT NULL UNIQUE,
    hashed_password VARCHAR(255) NOT NULL,

    first_name      VARCHAR(255),
    last_name       VARCHAR(255),

    avatar_url      VARCHAR(255),

    country         VARCHAR(255),
    region          VARCHAR(255)
);

CREATE UNIQUE INDEX users_email_lower_idx ON users (LOWER(email));
