package com.jakubolejarczyk.vet_server.dto.data.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDataDto {
    private String token;
}
