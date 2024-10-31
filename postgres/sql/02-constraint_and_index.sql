----------------------------
-- NON FOREIGN KEY CONSTRAINTS
----------------------------

-- RESOURCES TABLE
ALTER TABLE core.resources DROP CONSTRAINT IF EXISTS resources_pk;
ALTER TABLE core.resources ADD CONSTRAINT resources_pk PRIMARY KEY (id);

ALTER TABLE core.resources DROP CONSTRAINT IF EXISTS resources_name_unique;
ALTER TABLE core.resources ADD CONSTRAINT resources_name_unique UNIQUE (name);

-- RESOURCE_TYPES TABLE
ALTER TABLE core.resource_types DROP CONSTRAINT IF EXISTS resource_types_pk;
ALTER TABLE core.resource_types ADD CONSTRAINT resource_types_pk PRIMARY KEY (id);

ALTER TABLE core.resource_types DROP CONSTRAINT IF EXISTS resource_types_name_unique;
ALTER TABLE core.resource_types ADD CONSTRAINT resource_types_name_unique UNIQUE (name);

-- RESOURCE_DEFINITION TABLE
ALTER TABLE core.resource_definitions DROP CONSTRAINT IF EXISTS resource_definition_pk;
ALTER TABLE core.resource_definitions ADD CONSTRAINT resource_definition_pk PRIMARY KEY (id);

ALTER TABLE core.resource_definitions DROP CONSTRAINT IF EXISTS resource_definition_name_unique;
ALTER TABLE core.resource_definitions ADD CONSTRAINT resource_definition_name_unique UNIQUE (name);


-- REVISION_LOGS TABLE
ALTER TABLE core.revision_logs DROP CONSTRAINT IF EXISTS revision_logs_pk;
ALTER TABLE core.revision_logs ADD CONSTRAINT revision_logs_pk PRIMARY KEY (revision);

DROP INDEX IF EXISTS revision_logs_resource_name_idx;
CREATE INDEX revision_logs_resource_name_idx ON core.revision_logs (resource_name);

DROP INDEX IF EXISTS revision_logs_resource_id_idx;
CREATE INDEX revision_logs_resource_id_idx ON core.revision_logs (resource_id);

DROP INDEX IF EXISTS revision_logs_previous_revision_idx;
CREATE INDEX revision_logs_previous_revision_id_idx ON core.revision_logs (previous_revision);

-- JSON_OBJECTS TABLE
ALTER TABLE jsonstore.json_objects DROP CONSTRAINT IF EXISTS json_objects_pk;
ALTER TABLE jsonstore.json_objects ADD CONSTRAINT json_objects_pk PRIMARY KEY (id);



----------------------------
-- FOREIGN KEY CONSTRAINTS
----------------------------

-- RESOURCE_DEFINITION TABLE
ALTER TABLE core.resource_definitions DROP CONSTRAINT IF EXISTS resource_definitions_type_id_fk;
ALTER TABLE core.resource_definitions
    ADD CONSTRAINT resource_definitions_type_id_fk FOREIGN KEY (type_id) REFERENCES core.resource_types(id);

-- RESOURCES TABLE
ALTER TABLE core.resources DROP CONSTRAINT IF EXISTS resources_definition_id_fk;
ALTER TABLE core.resources
    ADD CONSTRAINT resources_definition_id_fk FOREIGN KEY (definition_id) REFERENCES core.resource_definitions(id);

-- REVISION_LOGS TABLE
ALTER TABLE core.revision_logs DROP CONSTRAINT IF EXISTS revision_logs_resource_id_fk;
ALTER TABLE core.revision_logs
    ADD CONSTRAINT revision_logs_resource_id_fk FOREIGN KEY (resource_id) REFERENCES core.resources(id);

ALTER TABLE core.revision_logs DROP CONSTRAINT IF EXISTS revision_logs_previous_revision_id_fk;
ALTER TABLE core.revision_logs
    ADD CONSTRAINT revision_logs_previous_revision_id_fk
        FOREIGN KEY (previous_revision_id) REFERENCES core.revision_logs(id);

-- JSON_OBJECTS TABLE
ALTER TABLE jsonstore.json_objects DROP CONSTRAINT IF EXISTS json_objects_resource_id_fk;
ALTER TABLE jsonstore.json_objects
    ADD CONSTRAINT json_objects_resource_id_fk FOREIGN KEY (resource_id) REFERENCES core.resources(id);
