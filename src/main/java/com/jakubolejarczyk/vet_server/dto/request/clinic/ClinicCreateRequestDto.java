package com.jakubolejarczyk.vet_server.dto.request.clinic;

import com.jakubolejarczyk.vet_server.domain.ClinicDomain;
import com.jakubolejarczyk.vet_server.dto.base.CrudCreateRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ClinicCreateRequestDto extends CrudCreateRequestDto<ClinicDomain> {
}
