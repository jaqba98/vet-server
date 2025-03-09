package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.clinic.ClinicCreateRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.clinic.ClinicCreateResponseDto;
import com.jakubolejarczyk.vet_server.service.database.ClinicService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ClinicController {
    private final ClinicService clinicService;

    @PostMapping("clinic")
    public ResponseEntity<ClinicCreateResponseDto> create(@Valid @RequestBody ClinicCreateRequestDto requestDto) {
        String name = requestDto.getName();
        clinicService.create(name);
        ClinicCreateResponseDto responseDto = ClinicCreateResponseDto.builder().success(true).build();
        return ResponseEntity.ok().body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ClinicCreateResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        ClinicCreateResponseDto responseDto = ClinicCreateResponseDto
                .builder()
                .success(false)
                .errors(errors)
                .build();
        return ResponseEntity.ok().body(responseDto);
    }
}
