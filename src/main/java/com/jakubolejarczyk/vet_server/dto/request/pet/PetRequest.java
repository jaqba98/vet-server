package com.jakubolejarczyk.vet_server.dto.request.pet;

import com.jakubolejarczyk.vet_server.domain.dependent.PetDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class PetRequest extends TokenRequest implements PetDomain {
    private Long id;

    @NotNull(message = "Is archived is required!")
    private Boolean isArchived;

    @NotNull(message = "Name is required!")
    @NotBlank(message = "Name cannot be empty!")
    @Size(max = 255, message = "Name cannot be longer than 255 characters!")
    private String entityName;

    @Size(max = 255, message = "Species cannot be longer than 255 characters!")
    private String species;

    @Size(max = 255, message = "Breed cannot be longer than 255 characters!")
    private String breed;

    private LocalDate dateOfBirth;

    private BigDecimal weightKg;

    @Size(max = 255, message = "Color cannot be longer than 255 characters!")
    private String color;

    private Boolean sterilized;

    @NotNull(message = "Picture url is required!")
    @NotBlank(message = "Picture url cannot be empty!")
    @Size(max = 255, message = "Picture url cannot be longer than 255 characters!")
    private String pictureUrl;

    @Size(max = 255, message = "Microchip Number cannot be longer than 255 characters!")
    private String microchipNumber;

    @Size(max = 255, message = "Medical Notes cannot be longer than 255 characters!")
    private String medicalNotes;

    @NotNull(message = "Client ID is required!")
    private Long clientId;
}
