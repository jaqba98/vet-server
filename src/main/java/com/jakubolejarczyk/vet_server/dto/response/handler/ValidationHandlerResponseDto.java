package com.jakubolejarczyk.vet_server.dto.response.handler;

import com.jakubolejarczyk.vet_server.dto.base.BaseResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ValidationHandlerResponseDto extends BaseResponseDto {
    private ArrayList<String> errors;
}
