package com.jakubolejarczyk.vet_server.dto.request;

import com.jakubolejarczyk.vet_server.dto.base.BaseRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class DeleteRequestDto extends BaseRequestDto {
    @NotNull(message = "Ids are required!")
    private ArrayList<Long> ids;
}
