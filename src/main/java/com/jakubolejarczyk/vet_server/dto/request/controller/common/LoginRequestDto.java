package com.jakubolejarczyk.vet_server.dto.request.controller.common;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
