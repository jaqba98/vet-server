package com.jakubolejarczyk.vet_server.dto.request.common;

import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChooseRoleRequest extends TokenRequest {
    @NotNull(message = "Role is requires!")
    @NotBlank(message = "Role cannot be empty!")
    @Pattern(regexp = "^(vet)$", message = "Role must be vet!")
    private String role;
}
