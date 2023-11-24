CREATE TYPE users.activation_code_status AS ENUM ('ACTIVE', 'NOT_ACTIVE');

CREATE TABLE users.activation_code (
    code_id UUID PRIMARY KEY,
    code VARCHAR(255),
    duration INTEGER,
    cost DOUBLE PRECISION,
    status users.activation_code_status NOT NULL,
    assigned_decoder_id VARCHAR(255),
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users."user"(user_id)
);
