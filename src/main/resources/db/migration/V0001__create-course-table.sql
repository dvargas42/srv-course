CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$

BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;

$$ LANGUAGE plpgsql;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS courses (
    id                  UUID            NOT NULL        UNIQUE      DEFAULT     uuid_generate_v4(),
    name                VARCHAR(100)    NOT NULL,
    category            VARCHAR(100)    NOT NULL,
    active              BOOLEAN         NOT NULL                    DEFAULT     FALSE,
    created_at          TIMESTAMP       NOT NULL                    DEFAULT     NOW(),
    updated_at          TIMESTAMP       NOT NULL                    DEFAULT     NOW(),

    PRIMARY KEY (id)
);

CREATE TRIGGER trigger_set_timestamp
BEFORE UPDATE ON courses
FOR EACH ROW
EXECUTE FUNCTION trigger_set_timestamp();
    

