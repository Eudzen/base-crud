CREATE TABLE IF NOT EXISTS food_types
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name  VARCHAR(50)                             NOT NULL,
    about VARCHAR(255),
    CONSTRAINT pk_food_types PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS type_name ON food_types (name);