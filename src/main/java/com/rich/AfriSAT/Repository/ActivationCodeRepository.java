package com.rich.AfriSAT.Repository;

import com.rich.AfriSAT.model.ActivationCode;
import com.rich.AfriSAT.model.ActivationCodeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActivationCodeRepository extends JpaRepository<ActivationCode, UUID> {
    Optional<ActivationCode> findByCode(String code);
    long countByStatus(ActivationCodeStatus status);
    @Query("SELECT SUM(ac.cost) FROM ActivationCode ac WHERE ac.status = :status")
    Double sumCostByStatus(@Param("status") ActivationCodeStatus status);

}
