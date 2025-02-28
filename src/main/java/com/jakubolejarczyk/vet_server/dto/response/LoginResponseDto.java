package com.jakubolejarczyk.vet_server.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDto {
    private Boolean success;

    private String token;
}
