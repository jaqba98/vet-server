package com.jakubolejarczyk.vet_server.dto.data.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PetData {
    private List<Pet> pets;
}
