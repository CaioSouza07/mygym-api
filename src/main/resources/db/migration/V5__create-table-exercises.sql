CREATE TABLE exercises (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    training_id UUID NOT NULL,
    name VARCHAR(150) NOT NULL,

    CONSTRAINT fk_exercise_training
       FOREIGN KEY (training_id)
           REFERENCES trainings(id)
           ON DELETE CASCADE
);