package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.service.security.JWTService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakubolejarczyk.vet_server.dto.request.AuthRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.AuthResponseDto;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AuthController {
    private final JWTService jwt;

    public ResponseEntity<AuthResponseDto> auth(@Valid @RequestBody AuthRequestDto requestDto) {
        String token = requestDto.getToken();
        Boolean isAuth = jwt.verifyToken(token);
        AuthResponseDto responseDto = new AuthResponseDto();
        responseDto.setIsAuth(isAuth);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
