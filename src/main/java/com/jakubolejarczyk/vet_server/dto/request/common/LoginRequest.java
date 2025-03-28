package com.jakubolejarczyk.vet_server.dto.request.common;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
