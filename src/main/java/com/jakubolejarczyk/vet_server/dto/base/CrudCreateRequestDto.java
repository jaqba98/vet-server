package com.jakubolejarczyk.vet_server.dto.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CrudCreateRequestDto<TData> extends BaseRequestDto {
    private TData data;
}
