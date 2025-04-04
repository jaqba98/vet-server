package com.jakubolejarczyk.vet_server.dto.data.logic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChooseRoleData {
    @NotNull(message = "Role is requires!")
    @NotBlank(message = "Role cannot be empty!")
    private String role;
}
