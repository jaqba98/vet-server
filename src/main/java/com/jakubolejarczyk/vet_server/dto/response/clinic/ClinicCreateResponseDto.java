package com.jakubolejarczyk.vet_server.dto.response.clinic;

import com.jakubolejarczyk.vet_server.domain.ClinicDomain;
import com.jakubolejarczyk.vet_server.dto.base.CrudReadResponseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ClinicCreateResponseDto extends CrudReadResponseDto<ClinicDomain> {
}
