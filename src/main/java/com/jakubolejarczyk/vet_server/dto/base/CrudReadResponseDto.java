package com.jakubolejarczyk.vet_server.dto.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CrudReadResponseDto<TData> extends BaseResponseDto {
    private TData data;
}
