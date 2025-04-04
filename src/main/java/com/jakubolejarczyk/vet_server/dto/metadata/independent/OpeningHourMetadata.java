package com.jakubolejarczyk.vet_server.dto.metadata.independent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OpeningHourMetadata {
    private BaseMetadata id;

    private BaseMetadata mondayFrom;

    private BaseMetadata mondayTo;

    private BaseMetadata tuesdayFrom;

    private BaseMetadata tuesdayTo;

    private BaseMetadata wednesdayFrom;

    private BaseMetadata wednesdayTo;

    private BaseMetadata thursdayFrom;

    private BaseMetadata thursdayTo;

    private BaseMetadata fridayFrom;

    private BaseMetadata fridayTo;

    private BaseMetadata saturdayFrom;

    private BaseMetadata saturdayTo;

    private BaseMetadata sundayFrom;

    private BaseMetadata sundayTo;
}
