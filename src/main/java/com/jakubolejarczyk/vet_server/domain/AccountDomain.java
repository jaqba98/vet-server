package com.jakubolejarczyk.vet_server.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDomain {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private String pictureUrl;
    private Boolean isVerified;
}
