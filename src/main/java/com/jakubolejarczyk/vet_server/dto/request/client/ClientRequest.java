package com.jakubolejarczyk.vet_server.dto.request.client;

import com.jakubolejarczyk.vet_server.domain.dependent.ClientDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.validator.unique.Unique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest extends TokenRequest implements ClientDomain {
    private Long id;

    @NotNull(message = "Is archived is required!")
    private Boolean isArchived;

    @NotNull(message = "Email is required!")
    @NotBlank(message = "Email cannot be empty!")
    @Size(max = 255, message = "Email cannot be longer than 255 characters!")
    @Unique(message = "There is a client with the given email!", table = "client", column = "email")
    private String email;

    @NotNull(message = "Phone number is required!")
    @NotBlank(message = "Phone number cannot be empty!")
    @Size(max = 255, message = "Phone number cannot be longer than 255 characters!")
    @Unique(message = "There is a client with the given phone number!", table = "client", column = "phoneNumber")
    private String phoneNumber;

    @NotNull(message = "First Name is required!")
    @NotBlank(message = "First Name cannot be empty!")
    @Size(max = 255, message = "First Name cannot be longer than 255 characters!")
    private String firstName;

    @NotNull(message = "Last Name is required!")
    @NotBlank(message = "Last Name cannot be empty!")
    @Size(max = 255, message = "Last Name cannot be longer than 255 characters!")
    private String lastName;

    @NotNull(message = "Clinic ID is required!")
    private Long clinicId;
}
