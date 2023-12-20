ALTER TABLE users.decoder
RENAME COLUMN last_modified_date TO updated_date;

ALTER TABLE users.decoder
RENAME COLUMN last_modified_by TO updated_by;

ALTER TABLE users.decoder
ALTER COLUMN created_by TYPE VARCHAR(255);

ALTER TABLE users.decoder
ALTER COLUMN updated_by TYPE VARCHAR(255);
