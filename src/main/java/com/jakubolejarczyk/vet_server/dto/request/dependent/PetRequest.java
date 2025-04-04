package com.jakubolejarczyk.vet_server.dto.request.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.PetDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PetRequest extends TokenRequest implements PetDomain {
    @NotNull(message = "Id is required!")
    private Long id;

    @NotNull(message = "Full name is required!")
    @NotBlank(message = "Full name cannot be empty!")
    @Size(max = 255, message = "Full name cannot be longer than 255 characters!")
    private String fullName;

    @Size(max = 255, message = "Species cannot be longer than 255 characters!")
    private String species;

    @Size(max = 255, message = "Breed cannot be longer than 255 characters!")
    private String breed;

    private LocalDate dateOfBirth;

    private BigDecimal weightKg;

    @Size(max = 255, message = "Color cannot be longer than 255 characters!")
    private String color;

    private Boolean sterilized;

    @Size(max = 255, message = "Picture url cannot be longer than 255 characters!")
    private String pictureUrl;

    @Size(max = 255, message = "Microchip Number cannot be longer than 255 characters!")
    private String microchipNumber;

    private String medicalNotes;

    @NotNull(message = "Client id is required!")
    private Long clientId;
}
