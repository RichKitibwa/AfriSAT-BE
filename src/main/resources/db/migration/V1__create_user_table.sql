CREATE TABLE "user" (
  user_id UUID PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255),
  password VARCHAR(255) NOT NULL,
  phone_number VARCHAR(20) NOT NULL,
  country VARCHAR(255) NOT NULL,
  date_created TIMESTAMP NOT NULL
);
