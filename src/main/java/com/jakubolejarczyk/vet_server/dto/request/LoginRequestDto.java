package com.jakubolejarczyk.vet_server.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class LoginRequestDto {
    private String email;

    private String password;
}
