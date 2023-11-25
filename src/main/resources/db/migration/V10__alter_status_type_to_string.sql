ALTER TABLE users.activation_code
ALTER COLUMN status TYPE VARCHAR USING status::text;
