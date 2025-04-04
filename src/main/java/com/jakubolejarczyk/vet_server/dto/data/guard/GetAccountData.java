package com.jakubolejarczyk.vet_server.dto.data.guard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAccountData {
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String pictureUrl;
}
