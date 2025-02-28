package com.jakubolejarczyk.vet_server.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponseDto {
    private Boolean isAuth;
}
