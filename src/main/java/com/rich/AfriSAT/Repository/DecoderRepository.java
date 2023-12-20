package com.rich.AfriSAT.Repository;

import com.rich.AfriSAT.model.Decoder;
import com.rich.AfriSAT.model.DecoderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DecoderRepository extends JpaRepository<Decoder, UUID> {
    List<Decoder> findAllByUser_userId(UUID userId);
    long countByDecoderStatus(DecoderStatus status);

}
