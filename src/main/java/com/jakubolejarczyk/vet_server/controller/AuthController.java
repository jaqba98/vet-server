package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.AuthRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.AuthResponseDto;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AuthController {
    private final TokenService tokenService;

    @PostMapping("auth")
    public ResponseEntity<AuthResponseDto> authPost(@RequestBody AuthRequestDto requestDto) {
        String token = requestDto.getToken();
        Boolean isAuth = tokenService.verify(token);
        AuthResponseDto responseDto = AuthResponseDto.builder().success(isAuth).build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
