package com.jakubolejarczyk.vet_server.dto.data.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class VetData {
    private List<Vet> vets;
}
