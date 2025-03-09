package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.logout.LogoutRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.logout.LogoutResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LogoutController {
    @PostMapping("logout")
    public ResponseEntity<LogoutResponseDto> logout(@Valid @RequestBody LogoutRequestDto requestDto) {
        LogoutResponseDto responseDto = new LogoutResponseDto(true, new ArrayList<>());
        return ResponseEntity.ok().body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<LogoutResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        LogoutResponseDto responseDto = new LogoutResponseDto(false, errors);
        return ResponseEntity.ok().body(responseDto);
    }
}
