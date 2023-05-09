CREATE SCHEMA IF NOT EXISTS client_service;

CREATE TABLE IF NOT EXISTS client_service.client(
    id uuid primary key,
    bank_id uuid not null,
    phone_number varchar not null,
    email varchar not null,
    source varchar not null
);

CREATE TABLE IF NOT EXISTS client_service.passports(
    id uuid primary key,
    client_id uuid not null references client_service.client(id),
    number varchar unique not null,
    first_name varchar not null,
    middle_name varchar not null,
    family_name varchar not null,
    birth_date date not null,
    birth_city varchar not null,
    is_deprecated bool not null
);

CREATE TABLE IF NOT EXISTS client_service.address(
     id uuid primary key,
     client_id uuid not null references client_service.client(id),
     country varchar not null,
     city varchar not null,
     street varchar not null,
     building varchar not null,
     apartment integer,
     type varchar not null,
     is_deprecated bool not null
);
