package com.jakubolejarczyk.vet_server.dto.response.guard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class IsVetResponseDto {
    private Boolean success;

    private ArrayList<String> errors;
}
