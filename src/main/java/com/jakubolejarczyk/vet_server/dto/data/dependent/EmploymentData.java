package com.jakubolejarczyk.vet_server.dto.data.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class EmploymentData {
    private List<Employment> employments;
}
