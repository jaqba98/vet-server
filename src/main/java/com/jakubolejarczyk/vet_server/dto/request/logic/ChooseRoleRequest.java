package com.jakubolejarczyk.vet_server.dto.request.logic;

import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChooseRoleRequest extends TokenRequest {
    @NotNull(message = "Role is requires!")
    @NotBlank(message = "Role cannot be empty!")
    private String role;
}
