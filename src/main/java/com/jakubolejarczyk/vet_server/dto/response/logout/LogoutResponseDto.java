package com.jakubolejarczyk.vet_server.dto.response.logout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class LogoutResponseDto {
    private Boolean success;

    private ArrayList<String> errors;
}
