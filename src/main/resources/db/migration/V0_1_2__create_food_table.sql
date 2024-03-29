CREATE TABLE IF NOT EXISTS food
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    type_id       BIGINT,
    name          VARCHAR(50)                             NOT NULL,
    proteins      DOUBLE PRECISION,
    fats          DOUBLE PRECISION,
    carbohydrates DOUBLE PRECISION,
    calories      INTEGER,
    is_deleted    BOOLEAN DEFAULT FALSE,
    CONSTRAINT pk_food PRIMARY KEY (id),
    CONSTRAINT FK_FOOD_ON_FOOD_TYPES FOREIGN KEY (type_id) REFERENCES food_types (id)
);

CREATE INDEX IF NOT EXISTS food_type ON food (type_id);