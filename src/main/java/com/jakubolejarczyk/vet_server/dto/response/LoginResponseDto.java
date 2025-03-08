package com.jakubolejarczyk.vet_server.dto.response;

import com.jakubolejarczyk.vet_server.dto.base.BaseResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class LoginResponseDto extends BaseResponseDto {
    private String token;
}
