package com.jakubolejarczyk.vet_server.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChooseRoleRequestDto {
    private String token;

    private String role;
}
