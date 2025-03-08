package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.clinic.ClinicCreateRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.clinic.ClinicCreateResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ClinicController {
    @PostMapping("clinic")
    public ResponseEntity<ClinicCreateResponseDto> create(@RequestBody ClinicCreateRequestDto requestDto) {
        String token = requestDto.getToken();
        String name = requestDto.getData().getName();
        System.out.println(token);
        System.out.println(name);
        ClinicCreateResponseDto responseDto = ClinicCreateResponseDto
                .builder()
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
