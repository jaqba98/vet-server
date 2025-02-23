package com.jakubolejarczyk.vet_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakubolejarczyk.vet_server.dto.request.RegistrationRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.RegistrationResponseDto;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController {
    @PostMapping("registration")
    public ResponseEntity<RegistrationResponseDto> registration(@RequestBody RegistrationRequestDto request) {
        RegistrationResponseDto response = new RegistrationResponseDto();
        response.setEmail(request.getEmail());
        response.setPassword(request.getPassword());
        response.setRepeatPassword(request.getRepeatPassword());
        response.setFirstName(request.getFirstName());
        response.setLastName(request.getLastName());
        response.setRole(request.getRole());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
