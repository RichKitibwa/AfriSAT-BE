package com.rich.AfriSAT.service;

import com.rich.AfriSAT.Exceptions.UserAlreadyExistsException;
import com.rich.AfriSAT.Repository.UserRepository;
import com.rich.AfriSAT.model.User;
import com.rich.AfriSAT.user.CustomUserDetails;
import com.rich.AfriSAT.user.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(RegistrationRequest request) {
        if (userRepository.findByUsername(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(String.format("User with username %s  already exists", request.getEmail()));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCountry(request.getCountry());

        return userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return CustomUserDetails.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .userId(user.getUser_id())
                .phoneNumber(user.getPhoneNumber())
                .country(user.getCountry())
                .role(user.getRole())
                .build();
    }
}
