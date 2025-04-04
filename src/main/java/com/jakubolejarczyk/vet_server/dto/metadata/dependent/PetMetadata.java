package com.jakubolejarczyk.vet_server.dto.metadata.dependent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class PetMetadata {
    private BaseMetadata id;

    private BaseMetadata fullName;

    private BaseMetadata species;

    private BaseMetadata breed;

    private BaseMetadata dateOfBirth;

    private BaseMetadata weightKg;

    private BaseMetadata color;

    private BaseMetadata sterilized;

    private BaseMetadata pictureUrl;

    private BaseMetadata microchipNumber;

    private BaseMetadata medicalNotes;

    private BaseMetadata clientId;
}
