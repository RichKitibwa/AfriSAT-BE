ALTER TABLE users.activation_code
ALTER COLUMN created_date DROP NOT NULL,
ALTER COLUMN updated_date DROP NOT NULL;
