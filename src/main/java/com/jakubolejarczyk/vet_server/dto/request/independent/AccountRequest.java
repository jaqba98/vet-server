package com.jakubolejarczyk.vet_server.dto.request.independent;

import com.jakubolejarczyk.vet_server.domain.independent.AccountDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest extends TokenRequest implements AccountDomain {
    @NotNull(message = "Id is required!")
    private Long id;

    @NotNull(message = "Email is required!")
    @NotBlank(message = "Email cannot be empty!")
    @Size(max = 255, message = "Email cannot be longer than 255 characters!")
    private String email;

    @NotNull(message = "Password is required!")
    @NotBlank(message = "Password cannot be empty!")
    @Size(max = 255, message = "Password cannot be longer than 255 characters!")
    private String password;

    @NotNull(message = "First name is required!")
    @NotBlank(message = "First name cannot be empty!")
    @Size(max = 50, message = "First name cannot be longer than 50 characters!")
    private String firstName;

    @NotNull(message = "Last name is required!")
    @NotBlank(message = "Last name cannot be empty!")
    @Size(max = 100, message = "Last name cannot be longer than 100 characters!")
    private String lastName;

    @NotBlank(message = "Role cannot be empty!")
    @Size(max = 50, message = "Role cannot be longer than 50 characters!")
    private String role;

    @NotBlank(message = "Role cannot be empty!")
    @Size(max = 255, message = "Picture url cannot be longer than 255 characters!")
    private String pictureUrl;
}
