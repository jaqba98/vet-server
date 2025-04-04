package com.jakubolejarczyk.vet_server.dto.data.guard;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GetAccountData {
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String pictureUrl;
}
