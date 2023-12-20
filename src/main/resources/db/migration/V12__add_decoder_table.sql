CREATE TABLE decoder (
    decoder_id UUID PRIMARY KEY,
    decoder_number VARCHAR(255) NOT NULL,
    user_id UUID NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_by UUID,
    last_modified_by UUID,

    CONSTRAINT fk_decoder_user FOREIGN KEY (user_id)
        REFERENCES users.user (user_id)
)
