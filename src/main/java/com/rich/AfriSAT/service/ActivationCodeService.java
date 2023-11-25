package com.rich.AfriSAT.service;

import com.rich.AfriSAT.Repository.ActivationCodeRepository;
import com.rich.AfriSAT.Repository.UserRepository;
import com.rich.AfriSAT.model.ActivationCode;
import com.rich.AfriSAT.model.ActivationCodeDTO;
import com.rich.AfriSAT.model.ActivationCodeStatus;
import com.rich.AfriSAT.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivationCodeService {

    @Autowired
    private ActivationCodeRepository activationCodeRepository;

    @Autowired
    private UserRepository userRepository;
    public ActivationCode addActivationCode(ActivationCodeDTO activationCodeDTO, UUID adminUserId) {

        if (activationCodeRepository.findByCode(activationCodeDTO.getCode()).isPresent()) {
            throw new IllegalStateException("Code already exists.");
        }

        User adminUser = userRepository.findById(adminUserId)
                .orElseThrow(() -> new RuntimeException("Admin user not found"));

        ActivationCode activationCode = ActivationCode.builder()
                .code(activationCodeDTO.getCode())
                .duration(activationCodeDTO.getDuration())
                .cost(activationCodeDTO.getCost())
                .assignedDecoderId(activationCodeDTO.getAssignedDecoderId())
                .user(adminUser)
                .status(ActivationCodeStatus.NOT_ACTIVE)
                .build();
        return activationCodeRepository.save(activationCode);
    }
}
