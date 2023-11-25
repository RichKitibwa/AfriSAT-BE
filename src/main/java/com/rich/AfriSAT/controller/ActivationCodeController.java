package com.rich.AfriSAT.controller;

import com.rich.AfriSAT.model.ActivationCode;
import com.rich.AfriSAT.model.ActivationCodeDTO;
import com.rich.AfriSAT.service.ActivationCodeService;
import com.rich.AfriSAT.user.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/activation-codes/")
public class ActivationCodeController {

    @Autowired
    private ActivationCodeService activationCodeService;

    @PostMapping("/add-code")
    public ResponseEntity<?> addActivationCode(@RequestBody ActivationCodeDTO activationCodeDTO, Principal principal) {
        try {
            Authentication authentication = (Authentication) principal;
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            UUID adminUserId = userDetails.getUserId();

            ActivationCode activationCode = activationCodeService.addActivationCode(activationCodeDTO, adminUserId);
            return ResponseEntity.ok(activationCode);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
