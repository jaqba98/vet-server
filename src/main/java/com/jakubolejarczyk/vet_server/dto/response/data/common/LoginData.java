package com.jakubolejarczyk.vet_server.dto.response.data.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginData {
    private String token;
}
