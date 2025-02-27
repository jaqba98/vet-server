package com.jakubolejarczyk.vet_server.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import com.jakubolejarczyk.vet_server.validator.email_not_exist.EmailNotExist;
import com.jakubolejarczyk.vet_server.validator.fields_match.FieldsMatch;

@Getter
@Setter
@FieldsMatch(first = "password", second = "confirmPassword", message = "Passwords must match!")
public class RegistrationRequestDto {
    @Email(message = "Incorrect email format!")
    @NotNull(message = "Email is required!")
    @NotBlank(message = "Email cannot be empty!")
    @Size(max = 255, message = "Email cannot be longer than 255 characters!")
    @EmailNotExist
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

    @NotNull(message = "Role is required!")
    @NotBlank(message = "Role cannot be empty!")
    @Size(max = 6, message = "Role cannot be longer than 6 characters!")
    @Pattern(regexp = "vet|client", message = "The role must be either a vet or a client!")
    private String role;
}
