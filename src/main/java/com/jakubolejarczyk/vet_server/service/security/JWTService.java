package com.jakubolejarczyk.vet_server.service.security;

import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

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

    public Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = getAlgorithm();
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC512("secret");
    }
}
