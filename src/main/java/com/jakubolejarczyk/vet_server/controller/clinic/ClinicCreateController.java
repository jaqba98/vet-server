package com.jakubolejarczyk.vet_server.controller.clinic;

import com.jakubolejarczyk.vet_server.dto.request.controller.clinic.ClinicCreateRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.controller.clinic.ClinicCreateResponseDto;
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
public class ClinicCreateController {
    private final ClinicService clinicService;

    @PostMapping("clinic-create")
    public ResponseEntity<ClinicCreateResponseDto> create(@Valid @RequestBody ClinicCreateRequestDto requestDto) {
        String name = requestDto.getName();
        clinicService.create(name);
        ClinicCreateResponseDto responseDto = new ClinicCreateResponseDto(true, new ArrayList<>());
        return ResponseEntity.ok().body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ClinicCreateResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        ClinicCreateResponseDto responseDto = new ClinicCreateResponseDto(false, errors);
        return ResponseEntity.ok().body(responseDto);
    }
}
