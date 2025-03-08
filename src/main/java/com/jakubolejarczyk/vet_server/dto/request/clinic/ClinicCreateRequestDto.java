package com.jakubolejarczyk.vet_server.dto.request.clinic;

import com.jakubolejarczyk.vet_server.domain.ClinicDomain;
import com.jakubolejarczyk.vet_server.dto.base.BaseRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class ClinicCreateRequestDto extends BaseRequestDto {
    private ClinicDomain data;
}
