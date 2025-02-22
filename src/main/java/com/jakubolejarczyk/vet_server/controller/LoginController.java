package com.jakubolejarczyk.vet_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakubolejarczyk.vet_server.dto.request.LoginRequestDto;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    @GetMapping
    public String login(@RequestBody LoginRequestDto dto) {
        return "Email: " + dto.getEmail() + ", Password: " + dto.getPassword();
    }
}
