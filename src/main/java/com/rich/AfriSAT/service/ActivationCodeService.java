package com.rich.AfriSAT.service;

import com.rich.AfriSAT.Repository.ActivationCodeRepository;
import com.rich.AfriSAT.Repository.DecoderRepository;
import com.rich.AfriSAT.Repository.UserRepository;
import com.rich.AfriSAT.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ActivationCodeService {

    @Autowired
    private ActivationCodeRepository activationCodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DecoderRepository decoderRepository;

    public ActivationCode addActivationCode(ActivationCodeDTO activationCodeDTO, UUID adminUserId) {

        if (activationCodeRepository.findByCode(activationCodeDTO.getCode()).isPresent()) {
            throw new IllegalStateException("Code already exists.");
        }

        User adminUser = userRepository.findById(adminUserId)
                .orElseThrow(() -> new RuntimeException("Admin user not found"));

        Decoder assignedDecoder = null;
        if (activationCodeDTO.getDecoderId() != null && !activationCodeDTO.getDecoderId().isEmpty()) {
            assignedDecoder = decoderRepository.findById(UUID.fromString(activationCodeDTO.getDecoderId()))
                    .orElseThrow(() -> new RuntimeException("Decoder not found"));
        }

        ActivationCode activationCode = ActivationCode.builder()
                .code(activationCodeDTO.getCode())
                .duration(activationCodeDTO.getDuration())
                .cost(activationCodeDTO.getCost())
                .assignedDecoder(assignedDecoder)
                .user(adminUser)
                .status(ActivationCodeStatus.NOT_ACTIVE)
                .build();
        return activationCodeRepository.save(activationCode);
    }

    public List<ActivationCodeViewDTO> findAllActivationCodes() {
        return activationCodeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ActivationCodeViewDTO convertToDTO(ActivationCode activationCode) {
        ActivationCodeViewDTO dto = new ActivationCodeViewDTO();
        dto.setCode(activationCode.getCode());
        dto.setDuration(activationCode.getDuration());
        dto.setCost(activationCode.getCost());
        if (activationCode.getAssignedDecoder() != null) {
            dto.setAssignedDecoderId(activationCode.getAssignedDecoder().getDecoderId().toString());
        } else {
            dto.setAssignedDecoderId(null);
        }
        dto.setStatus(activationCode.getStatus());
        return dto;
    }
}
