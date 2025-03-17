package com.jakubolejarczyk.vet_server.controller.clinic;

import com.jakubolejarczyk.vet_server.dto.request.controller.clinic.ClinicUpdateRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.controller.clinic.ClinicUpdateResponseDto;
import com.jakubolejarczyk.vet_server.model.Clinic;
import com.jakubolejarczyk.vet_server.service.database.ClinicService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ClinicUpdateController {
    private final ClinicService clinicService;

    @PostMapping("clinic-update")
    public ResponseEntity<ClinicUpdateResponseDto> create(@Valid @RequestBody ClinicUpdateRequestDto requestDto) {
        Long id = requestDto.getId();
        String name = requestDto.getName();
        Optional<Clinic> clinic = clinicService.get(id);
        if (clinic.isEmpty()) {
            ClinicUpdateResponseDto responseDto = new ClinicUpdateResponseDto(false, new ArrayList<>());
            return ResponseEntity.ok().body(responseDto);
        }
        Clinic newClinic = clinic.get();
        newClinic.setId(id);
        newClinic.setName(name);
        clinicService.update(newClinic);
        ClinicUpdateResponseDto responseDto = new ClinicUpdateResponseDto(true, new ArrayList<>());
        return ResponseEntity.ok().body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ClinicUpdateResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        ClinicUpdateResponseDto responseDto = new ClinicUpdateResponseDto(false, errors);
        return ResponseEntity.ok().body(responseDto);
    }
}