CREATE SCHEMA IF NOT EXISTS client_service;

CREATE TABLE IF NOT EXISTS client_service.client(
    id uuid primary key,
    bank_id uuid,
    phone_number varchar,
    email varchar,
    source varchar not null
);

CREATE TABLE IF NOT EXISTS client_service.passports(
    id uuid primary key,
    client_id uuid not null references client_service.client(id),
    number varchar unique,
    first_name varchar,
    middle_name varchar,
    family_name varchar,
    birth_date date,
    birth_city varchar,
    is_deprecated bool not null
);

CREATE TABLE IF NOT EXISTS client_service.address(
     id uuid primary key,
     client_id uuid not null references client_service.client(id),
     country varchar,
     city varchar,
     street varchar,
     building varchar,
     apartment integer,
     type varchar not null,
     is_deprecated bool not null
);
