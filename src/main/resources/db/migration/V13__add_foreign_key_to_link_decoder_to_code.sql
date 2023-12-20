ALTER TABLE users.activation_code
DROP COLUMN IF EXISTS assignedDecoderId;

ALTER TABLE users.activation_code
ADD COLUMN decoder_id UUID;

ALTER TABLE users.activation_code
ADD CONSTRAINT fk_activation_code_decoder FOREIGN KEY (decoder_id)
REFERENCES users.decoder (decoder_id);
