UPDATE users.decoder
SET decoder_status = 'NOT_ACTIVE'
WHERE decoder_status IS NULL;
