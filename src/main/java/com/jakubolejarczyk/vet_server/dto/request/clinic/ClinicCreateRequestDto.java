package com.jakubolejarczyk.vet_server.dto.request.clinic;

import com.jakubolejarczyk.vet_server.dto.base.BaseRequestDto;
import com.jakubolejarczyk.vet_server.validator.unique.Unique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ClinicCreateRequestDto extends BaseRequestDto {
    @NotNull(message = "Name is requires!")
    @NotBlank(message = "Name cannot be empty!")
    @Unique(message = "There is a clinic with the given name!", table = "clinic", column = "name")
    private String name;
}
