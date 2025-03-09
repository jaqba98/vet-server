package com.jakubolejarczyk.vet_server.dto.request.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String email;

    private String password;
}
