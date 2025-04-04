package com.jakubolejarczyk.vet_server.dto.data.guard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetAccountData {
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String pictureUrl;
}
