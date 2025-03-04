package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.LogoutRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.LogoutResponseDto;
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
public class LogoutController {
    @PostMapping("logout")
    public ResponseEntity<LogoutResponseDto> logoutPost(@RequestBody LogoutRequestDto requestDto) {
        LogoutResponseDto responseDto = LogoutResponseDto.builder()
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
