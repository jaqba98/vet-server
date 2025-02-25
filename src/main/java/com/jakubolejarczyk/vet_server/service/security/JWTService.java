package com.jakubolejarczyk.vet_server.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JWTService {
    public String generateToken(String email) {
        Algorithm algorithm = getAlgorithm();
        return JWT
                .create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                .sign(algorithm);
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC512("aaaaaa");
    }
}
