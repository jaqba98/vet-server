package com.jakubolejarczyk.vet_server.dto.data.independent;

import com.jakubolejarczyk.vet_server.model.independent.OpeningHour;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OpeningHourData {
    private List<OpeningHour> openingHours;
}
