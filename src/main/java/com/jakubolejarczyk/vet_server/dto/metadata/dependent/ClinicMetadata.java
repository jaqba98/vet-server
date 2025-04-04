package com.jakubolejarczyk.vet_server.dto.metadata.dependent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClinicMetadata {
    private BaseMetadata id;

    private BaseMetadata fullName;

    private BaseMetadata street;

    private BaseMetadata buildingNumber;

    private BaseMetadata apartmentNumber;

    private BaseMetadata postalCode;

    private BaseMetadata city;

    private BaseMetadata province;

    private BaseMetadata country;

    private BaseMetadata email;

    private BaseMetadata phoneNumber;

    private BaseMetadata openingHourId;
}
