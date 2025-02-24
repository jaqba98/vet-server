package com.jakubolejarczyk.vet_server.dto.response;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationResponseDto {
    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("errors")
    private Map<String, String> errors;
}
