package com.jakubolejarczyk.vet_server.dto.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class BaseResponseDto {
    private Boolean success;
}
