package com.jakubolejarczyk.vet_server.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequestDto {
    @JsonProperty("email")
    @Email
    @NotNull(message = "Email is required!")
    @NotBlank(message = "Email cannot be empty!")
    @Size(max = 255, message = "Email cannot be longer than 255 characters!")
    private String email;

    @JsonProperty("password")
    @NotNull(message = "Password is required!")
    @NotBlank(message = "Password cannot be empty!")
    @Size(max = 255, message = "Password cannot be longer than 255 characters!")
    private String password;

    @JsonProperty("confirm_password")
    @NotNull(message = "Confirm password is required!")
    @NotBlank(message = "Confirm password cannot be empty!")
    @Size(max = 255, message = "Confirm password cannot be longer than 255 characters!")
    private String confirmPassword;

    @JsonProperty("first_name")
    @NotNull(message = "First name is required!")
    @NotBlank(message = "First name cannot be empty!")
    @Size(max = 50, message = "First name cannot be longer than 50 characters!")
    private String firstName;

    @JsonProperty("last_name")
    @NotNull(message = "Last name is required!")
    @NotBlank(message = "Last name cannot be empty!")
    @Size(max = 100, message = "Last name cannot be longer than 100 characters!")
    private String lastName;

    @JsonProperty("role")
    @NotNull(message = "Role is required!")
    @NotBlank(message = "Role cannot be empty!")
    @Size(max = 6, message = "Role cannot be longer than 6 characters!")
    @Pattern(regexp = "vet|client", message = "The role must be either a vet or a client!")
    private String role;
}
