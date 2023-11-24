package com.rich.AfriSAT.controller;

import com.rich.AfriSAT.config.AuthResponse;
import com.rich.AfriSAT.config.JwtUtil;
import com.rich.AfriSAT.service.UserService;
import com.rich.AfriSAT.user.CustomUserDetails;
import com.rich.AfriSAT.user.LoginRequest;
import com.rich.AfriSAT.user.RegistrationRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid RegistrationRequest request) {

        userService.createUser(request);
        CustomUserDetails userDetails = (CustomUserDetails) userService.loadUserByUsername(request.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(generateAuthResponse(userDetails));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(generateAuthResponse(userDetails));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();

        return ResponseEntity.ok("Logout successful");
    }

    private AuthResponse generateAuthResponse(CustomUserDetails userDetails) {
        final String token = jwtUtil.generateToken(userDetails);
        final Date tokenExpiration = jwtUtil.extractExpiration(token);

        return AuthResponse.builder()
                .userId(userDetails.getUserId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .phoneNumber(userDetails.getPhoneNumber())
                .country(userDetails.getCountry())
                .token(token)
                .role(userDetails.getRole())
                .expiresAt(tokenExpiration)
                .build();
    }
}
