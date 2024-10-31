-- CREATING SCHEMAS --
DROP SCHEMA IF EXISTS core CASCADE;
CREATE SCHEMA core;

DROP SCHEMA IF EXISTS tbstore CASCADE;
CREATE SCHEMA tbstore;

DROP SCHEMA IF EXISTS jsonstore CASCADE;
CREATE SCHEMA jsonstore;

-- CREATING TABLES --

-- core schema
CREATE TABLE core.resources (
    id BIGSERIAL NOT NULL,
    name VARCHAR NOT NULL,
    definition_id INTEGER NOT NULL,
    value BYTEA,
    lease_id INTEGER
);

CREATE TABLE core.resource_types (
    id SERIAL NOT NULL,
    name VARCHAR NOT NULL,
    schema JSONB NOT NULL
);

CREATE TABLE core.resource_definitions (
    id SERIAL NOT NULL,
    name VARCHAR UNIQUE NOT NULL,
    type_id INTEGER NOT NULL,
    definition JSONB NOT NULL
);

CREATE TABLE core.revision_logs (
    revision BIGSERIAL NOT NULL,
    previous_revision BIGINT,
    resource_name VARCHAR NOT NULL,
    resource_id INTEGER NOT NULL,
    operation VARCHAR NOT NULL,
    log_data BYTEA NOT NULL
);

-- jsonstore schema
CREATE TABLE jsonstore.json_objects (
    id BIGSERIAL NOT NULL,
    data JSONB NOT NULL,
    resource_id INTEGER NOT NULL
);














