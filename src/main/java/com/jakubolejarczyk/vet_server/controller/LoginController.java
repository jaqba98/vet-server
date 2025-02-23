package com.jakubolejarczyk.vet_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jakubolejarczyk.vet_server.dto.request.LoginRequestDto;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDto dto) {
        Map<String, Object> response = new HashMap<>();
        response.put("email", dto.getEmail());
        response.put("password", dto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
