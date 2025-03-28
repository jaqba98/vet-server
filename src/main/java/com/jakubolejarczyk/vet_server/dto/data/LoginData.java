package com.jakubolejarczyk.vet_server.dto.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginData {
    private String token;
}
