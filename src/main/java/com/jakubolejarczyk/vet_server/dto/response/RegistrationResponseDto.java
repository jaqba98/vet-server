package com.jakubolejarczyk.vet_server.dto.response;

import com.jakubolejarczyk.vet_server.dto.base.BaseResponseDto;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@SuperBuilder
public class RegistrationResponseDto extends BaseResponseDto {
    private ArrayList<String> errors;
}
