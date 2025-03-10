package com.jakubolejarczyk.vet_server.controller.clinic;

import com.jakubolejarczyk.vet_server.dto.request.controller.clinic.ClinicDeleteRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.controller.clinic.ClinicDeleteResponseDto;
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
public class ClinicDeleteController {
    private final ClinicService clinicService;

    @PostMapping("clinic-delete")
    public ResponseEntity<ClinicDeleteResponseDto> read(@Valid @RequestBody ClinicDeleteRequestDto requestDto) {
        requestDto.getIds().forEach(clinicService::delete);
        ClinicDeleteResponseDto responseDto = new ClinicDeleteResponseDto(true, new ArrayList<>());
        return ResponseEntity.ok().body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ClinicDeleteResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        ClinicDeleteResponseDto responseDto = new ClinicDeleteResponseDto(false, errors);
        return ResponseEntity.ok().body(responseDto);
    }
}
