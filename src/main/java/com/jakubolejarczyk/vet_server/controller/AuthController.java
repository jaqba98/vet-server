package com.jakubolejarczyk.vet_server.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakubolejarczyk.vet_server.dto.response.AuthorizeResponseDto;
import com.jakubolejarczyk.vet_server.dto.request.AuthorizeRequestDto;
import com.jakubolejarczyk.vet_server.service.security.JWTService;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AuthController {
    protected final JWTService jwtService;

    @PostMapping("auth")
    public ResponseEntity<AuthorizeResponseDto> authorize(@RequestBody AuthorizeRequestDto request) {
        Boolean isCorrect = jwtService.verifyToken(request.getToken());
        AuthorizeResponseDto response = new AuthorizeResponseDto();
        response.setIsAuthorized(isCorrect);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
