package com.rich.AfriSAT.controller;

import com.rich.AfriSAT.model.AddDecoderRequest;
import com.rich.AfriSAT.model.Decoder;
import com.rich.AfriSAT.service.DecoderService;
import com.rich.AfriSAT.user.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/decoders/")
public class DecoderController {

    @Autowired
    private DecoderService decoderService;

    @PostMapping("/add-decoder")
    public ResponseEntity<?> addDecoder(@RequestBody AddDecoderRequest request, Principal principal) {

        UUID userId = getCurrentUserId(principal);
        Decoder decoder = decoderService.addDecoder(request.getDecoderNumber(), userId);
        return new ResponseEntity<>(decoder.getDecoderId(), HttpStatus.CREATED);
    }

    @GetMapping("/all-decoders")
    public ResponseEntity<List<Decoder>> getAllDecodersForUser(Principal principal) {
        UUID userId = getCurrentUserId(principal);
        List<Decoder> decoders = decoderService.getDecodersByUserId(userId);
        return ResponseEntity.ok(decoders);
    }

    private UUID getCurrentUserId(Principal principal) {
        Authentication authentication = (Authentication) principal;
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUserId();
    }
}
