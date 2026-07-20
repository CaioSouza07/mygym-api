CREATE TABLE history (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    exercise_id UUID NOT NULL,
    user_id UUID NOT NULL,
    weight NUMERIC(3,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_history_exercise
      FOREIGN KEY (exercise_id)
          REFERENCES exercises(id),
    CONSTRAINT fk_history_user
      FOREIGN KEY (user_id)
          REFERENCES users(id)
);