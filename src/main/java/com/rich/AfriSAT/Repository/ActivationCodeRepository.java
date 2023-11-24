package com.rich.AfriSAT.Repository;

import com.rich.AfriSAT.model.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActivationCodeRepository extends JpaRepository<ActivationCode, UUID> {

}
