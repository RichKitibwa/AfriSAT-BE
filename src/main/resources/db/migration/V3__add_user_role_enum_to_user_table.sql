CREATE TYPE users.user_role AS ENUM ('CLIENT', 'ADMIN');

ALTER TABLE users."user"
ADD COLUMN role users.user_role NOT NULL DEFAULT 'CLIENT';
