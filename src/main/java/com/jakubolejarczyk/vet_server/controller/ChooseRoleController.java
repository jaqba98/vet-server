package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.controller.ChooseRoleRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.controller.ChooseRoleResponseDto;
import com.jakubolejarczyk.vet_server.service.database.AccountService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ChooseRoleController {
    private final TokenService tokenService;

    private final AccountService accountService;

    @PostMapping("choose-role")
    public ResponseEntity<ChooseRoleResponseDto> chooseRolePost(@Valid @RequestBody ChooseRoleRequestDto requestDto) {
        String token = requestDto.getToken();
        String role = requestDto.getRole();
        String email = tokenService.decode(token);
        accountService.updateRole(email, role);
        ChooseRoleResponseDto responseDto = new ChooseRoleResponseDto(true, new ArrayList<>(), role);
        return ResponseEntity.ok().body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ChooseRoleResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        ChooseRoleResponseDto responseDto = new ChooseRoleResponseDto(false, errors, "");
        return ResponseEntity.ok().body(responseDto);
    }
}
