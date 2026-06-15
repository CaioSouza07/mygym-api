CREATE TABLE series (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    exercise_id UUID NOT NULL,
    repetitions INT NOT NULL,
    order_index INT NOT NULL,

    CONSTRAINT fk_serie_exercise
        FOREIGN KEY (exercise_id)
            REFERENCES exercises(id)
            ON DELETE CASCADE
);