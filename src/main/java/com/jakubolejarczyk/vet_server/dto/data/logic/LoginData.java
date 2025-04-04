package com.jakubolejarczyk.vet_server.dto.data.logic;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class LoginData {
    private String token;
}
