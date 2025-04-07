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
public class CreatePetStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final PetService petService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("petRequest")) throw new Error("The petRequest is required!");
        val petRequest = stepStore.getItem("petRequest", Pet.class);
        val pet = Pet.builder()
            .fullName(petRequest.getFullName())
            .species(petRequest.getSpecies())
            .breed(petRequest.getBreed())
            .dateOfBirth(petRequest.getDateOfBirth())
            .weightKg(petRequest.getWeightKg())
            .color(petRequest.getColor())
            .sterilized(petRequest.getSterilized())
            .pictureUrl(petRequest.getPictureUrl())
            .microchipNumber(petRequest.getMicrochipNumber())
            .medicalNotes(petRequest.getMedicalNotes())
            .clientId(petRequest.getClientId())
            .build();
        val petData = petService.save(pet);
        stepStore.setItem("petData", petData);
    }
}
