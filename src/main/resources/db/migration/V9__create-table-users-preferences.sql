CREATE TABLE user_preferences (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL UNIQUE,
    default_rest_time INTEGER NOT NULL DEFAULT 60,

    CONSTRAINT fk_user_preferences_user
      FOREIGN KEY (user_id)
          REFERENCES users(id)
          ON DELETE CASCADE
);
