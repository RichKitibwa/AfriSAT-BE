package com.rich.AfriSAT.service;

import com.rich.AfriSAT.Repository.DecoderRepository;
import com.rich.AfriSAT.Repository.UserRepository;
import com.rich.AfriSAT.model.Decoder;
import com.rich.AfriSAT.model.DecoderStatus;
import com.rich.AfriSAT.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DecoderService {

    @Autowired
    private DecoderRepository decoderRepository;

    @Autowired
    private UserRepository userRepository;

    public Decoder addDecoder(String decoderNumber, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Decoder decoder = Decoder.builder()
                .decoderNumber(decoderNumber)
                .user(user)
                .decoderStatus(DecoderStatus.NOT_ACTIVE)
                .build();
        return decoderRepository.save(decoder);
    }

    public List<Decoder> getDecodersByUserId(UUID userId) {
        return decoderRepository.findAllByUser_userId(userId);
    }
}
