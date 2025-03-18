package com.jakubolejarczyk.vet_server.dto.request.crud;

import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class DeleteRequestDto extends TokenRequestDto {
    @NotNull(message = "Ids are required!")
    private ArrayList<Long> ids;
}
