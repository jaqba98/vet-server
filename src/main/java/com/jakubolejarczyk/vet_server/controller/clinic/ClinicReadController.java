package com.jakubolejarczyk.vet_server.controller.clinic;

import com.jakubolejarczyk.vet_server.dto.request.controller.clinic.ClinicReadRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.controller.clinic.ClinicReadResponseDto;
import com.jakubolejarczyk.vet_server.model.Clinic;
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
public class ClinicReadController {
    private final ClinicService clinicService;

    @PostMapping("clinic-read")
    public ResponseEntity<ClinicReadResponseDto> read(@Valid @RequestBody ClinicReadRequestDto requestDto) {
        ArrayList<Clinic> clinics = clinicService.read();
        ClinicReadResponseDto responseDto = new ClinicReadResponseDto(true, new ArrayList<>(), clinics);
        return ResponseEntity.ok().body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ClinicReadResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        ClinicReadResponseDto responseDto = new ClinicReadResponseDto(false, errors, new ArrayList<>());
        return ResponseEntity.ok().body(responseDto);
    }
}
