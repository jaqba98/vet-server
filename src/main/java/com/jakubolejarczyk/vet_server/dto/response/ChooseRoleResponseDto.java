package com.jakubolejarczyk.vet_server.dto.response;

import com.jakubolejarczyk.vet_server.dto.base.BaseResponseDto;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ChooseRoleResponseDto extends BaseResponseDto {
    private String role;
}
