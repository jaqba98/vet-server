package com.jakubolejarczyk.vet_server.security;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordService {
    private final PasswordEncoder passwordEncoder;

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public Boolean match(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }
}
