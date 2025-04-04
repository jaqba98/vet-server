package com.jakubolejarczyk.vet_server.dto.request.logic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    @NotNull(message = "Email is required!")
    @NotBlank(message = "Email cannot be empty!")
    @Size(max = 255, message = "Email cannot be longer than 255 characters!")
    private String email;

    @NotNull(message = "Password is required!")
    @NotBlank(message = "Password cannot be empty!")
    @Size(max = 255, message = "Password cannot be longer than 255 characters!")
    private String password;

    @NotNull(message = "Confirm password is required!")
    @NotBlank(message = "Confirm password cannot be empty!")
    @Size(max = 255, message = "Confirm password cannot be longer than 255 characters!")
    private String confirmPassword;

    @NotNull(message = "First name is required!")
    @NotBlank(message = "First name cannot be empty!")
    @Size(max = 50, message = "First name cannot be longer than 50 characters!")
    private String firstName;

    @NotNull(message = "Last name is required!")
    @NotBlank(message = "Last name cannot be empty!")
    @Size(max = 100, message = "Last name cannot be longer than 100 characters!")
    private String lastName;
}
