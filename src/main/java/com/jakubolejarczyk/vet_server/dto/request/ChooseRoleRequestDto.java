package com.jakubolejarczyk.vet_server.dto.request;

import com.jakubolejarczyk.vet_server.dto.base.BaseRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChooseRoleRequestDto extends BaseRequestDto {
    private String role;
}
