package com.jakubolejarczyk.vet_server.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakubolejarczyk.vet_server.dto.request.LogoutRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.LogoutResponseDto;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LogoutController {
    @PostMapping("logout")
    public ResponseEntity<LogoutResponseDto> auth(@RequestBody LogoutRequestDto requestDto) {
        LogoutResponseDto response = new LogoutResponseDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
