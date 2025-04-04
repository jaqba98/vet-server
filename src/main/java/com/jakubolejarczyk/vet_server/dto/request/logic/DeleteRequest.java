package com.jakubolejarczyk.vet_server.dto.request.logic;

import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class DeleteRequest extends TokenRequest {
    @NotNull(message = "Ids are required!")
    private ArrayList<Long> ids;
}
