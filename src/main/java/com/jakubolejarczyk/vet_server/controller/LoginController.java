package com.jakubolejarczyk.vet_server.controller;

import org.springframework.web.bind.annotation.*;

import com.jakubolejarczyk.vet_server.dto.request.LoginRequestDto;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    @PostMapping
    public String login(@RequestBody LoginRequestDto dto) {
        return "Email: " + dto.getEmail() + ", Password: " + dto.getPassword();
    }
}
