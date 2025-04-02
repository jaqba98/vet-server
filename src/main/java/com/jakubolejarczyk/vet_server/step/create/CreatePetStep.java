package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import com.jakubolejarczyk.vet_server.model.dependent.Service;
import com.jakubolejarczyk.vet_server.service.dependent.PetService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;

@Service
@AllArgsConstructor
public class CreatePetStep implements StepModel {
    private final PetService petService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestPet")) throw new Error("The requestPet is required!");
        val requestPet = stepStore.getItem("requestPet", Pet.class);
        val pet = Pet.builder()
                .isArchived(false)
                .name(requestPet.getName())
                .species(requestPet.getSpecies())
                .breed(requestPet.getBreed())
                .dateOfBirth(requestPet.getDateOfBirth())
                .weightKg(requestPet.getWeightKg())
                .color(requestPet.getColor())
                .sterilized(requestPet.getSterilized())
                .pictureUrl(requestPet.getPictureUrl())
                .microchipNumber(requestPet.getMicrochipNumber())
                .medicalNotes(requestPet.getMedicalNotes())
                .clientId(requestPet.getClientId())
                .build();
        petService.create(pet);
    }
}
