package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import com.jakubolejarczyk.vet_server.service.dependent.PetService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePetStepRunner implements StepRunnerModel {
    private final PetService petService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestPet")) throw new Error("The requestPet is required!");
        val requestPet = stepStore.getItem("requestPet", Pet.class);
        val pet = Pet.builder()
                .fullName(requestPet.getFullName())
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
        petService.save(pet);
    }
}
