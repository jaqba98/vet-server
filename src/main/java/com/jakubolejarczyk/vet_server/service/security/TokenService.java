package com.jakubolejarczyk.vet_server.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    public String generate(String email) {
        Algorithm algorithm = getAlgorithm();
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                .sign(algorithm);
    }

    public Boolean verify(String token) {
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

    public String decode(String token) {
        return JWT.decode(token).getSubject();
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC512("vet-server-secret-key");
    }
}
