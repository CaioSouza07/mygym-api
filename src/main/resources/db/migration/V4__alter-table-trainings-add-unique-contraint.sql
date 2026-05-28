ALTER TABLE trainings
ADD CONSTRAINT uk_training_user_week_day
UNIQUE (user_id, week_day);