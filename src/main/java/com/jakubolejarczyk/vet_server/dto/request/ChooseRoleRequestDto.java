package com.jakubolejarczyk.vet_server.dto.request;

import com.jakubolejarczyk.vet_server.dto.base.BaseRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ChooseRoleRequestDto extends BaseRequestDto {
    private String role;
}
