package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.clinic.ClinicCreateRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.clinic.ClinicCreateResponseDto;
import com.jakubolejarczyk.vet_server.service.database.ClinicService;
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
public class ClinicController {
    private final TokenService tokenService;

    private final ClinicService clinicService;

    @PostMapping("clinic")
    public ResponseEntity<ClinicCreateResponseDto> create(@RequestBody ClinicCreateRequestDto requestDto) {
        String token = requestDto.getToken();
        if (!tokenService.verify(token)) {
            ClinicCreateResponseDto responseDto = ClinicCreateResponseDto.builder().success(false).build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        String name = requestDto.getData().getName();
        this.clinicService.create(name);
        ClinicCreateResponseDto responseDto = ClinicCreateResponseDto.builder().success(true).build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
