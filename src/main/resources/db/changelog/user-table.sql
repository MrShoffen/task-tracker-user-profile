--liquibase formatted sql


--changeset mrshoffen:2
CREATE TABLE IF NOT EXISTS users
(
    id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    avatar_url VARCHAR(256)
);

CREATE UNIQUE INDEX users_email_lower_idx ON users (LOWER(email));