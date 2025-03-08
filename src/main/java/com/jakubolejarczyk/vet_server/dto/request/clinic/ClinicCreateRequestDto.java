package com.jakubolejarczyk.vet_server.dto.request.clinic;

import com.jakubolejarczyk.vet_server.dto.base.BaseRequestDto;
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
    // todo: Add verification whether name is busy!
    private String name;
}
