package com.rich.AfriSAT.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private UUID userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String country;
    private String token;
    private Date expiresAt;
}
