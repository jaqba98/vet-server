package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import com.jakubolejarczyk.vet_server.service.dependent.PetService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdatePetStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final PetService petService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("petRequest")) throw new Error("The petRequest is required!");
        val petRequest = stepStore.getItem("petRequest", Pet.class);
        val petId = petRequest.getId();
        val currentPet = petService.findById(petId);
        if (currentPet.isEmpty()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("Failed to update a pet.");
            return;
        }
        currentPet.get().setFullName(petRequest.getFullName());
        currentPet.get().setSpecies(petRequest.getSpecies());
        currentPet.get().setBreed(petRequest.getBreed());
        currentPet.get().setDateOfBirth(petRequest.getDateOfBirth());
        currentPet.get().setWeightKg(petRequest.getWeightKg());
        currentPet.get().setColor(petRequest.getColor());
        currentPet.get().setSterilized(petRequest.getSterilized());
        currentPet.get().setPictureUrl(petRequest.getPictureUrl());
        currentPet.get().setMicrochipNumber(petRequest.getMicrochipNumber());
        currentPet.get().setMedicalNotes(petRequest.getMedicalNotes());
        currentPet.get().setClientId(petRequest.getClientId());
        val petData = petService.save(currentPet.get());
        stepStore.setItem("petData", petData);
    }
}
