package com.jakubolejarczyk.vet_server.dto.request.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;
}
