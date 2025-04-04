package com.jakubolejarczyk.vet_server.dto.request.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.ClientDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest extends TokenRequest implements ClientDomain {
    @NotNull(message = "Id is required!")
    private Long id;

    @NotNull(message = "Email is required!")
    @NotBlank(message = "Email cannot be empty!")
    @Size(max = 255, message = "Email cannot be longer than 255 characters!")
    private String email;

    @NotNull(message = "Phone number is required!")
    @NotBlank(message = "Phone number cannot be empty!")
    @Size(max = 20, message = "Phone number cannot be longer than 20 characters!")
    private String phoneNumber;

    @NotNull(message = "First Name is required!")
    @NotBlank(message = "First Name cannot be empty!")
    @Size(max = 50, message = "First Name cannot be longer than 50 characters!")
    private String firstName;

    @NotNull(message = "Last Name is required!")
    @NotBlank(message = "Last Name cannot be empty!")
    @Size(max = 100, message = "Last Name cannot be longer than 100 characters!")
    private String lastName;

    @NotNull(message = "Clinic id is required!")
    private Long clinicId;
}
