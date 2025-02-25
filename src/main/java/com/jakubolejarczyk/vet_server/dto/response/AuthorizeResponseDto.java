package com.jakubolejarczyk.vet_server.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizeResponseDto {
    private Boolean isAuthorized;
}
