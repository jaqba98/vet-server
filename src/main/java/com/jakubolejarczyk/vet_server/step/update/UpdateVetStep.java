package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.service.dependent.VetService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateVetStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final VetService vetService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("vetRequest")) throw new Error("The vetRequest is required!");
        val vetRequest = stepStore.getItem("vetRequest", Vet.class);
        val vetId = vetRequest.getId();
        val currentVet = vetService.findById(vetId);
        if (currentVet.isEmpty()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("Failed to update a vet.");
            return;
        }
        currentVet.get().setLicenseNumber(vetRequest.getLicenseNumber());
        currentVet.get().setLicenseIssueDate(vetRequest.getLicenseIssueDate());
        currentVet.get().setLicenseExpiryDate(vetRequest.getLicenseExpiryDate());
        currentVet.get().setSpecialization(vetRequest.getSpecialization());
        currentVet.get().setYearsOfExperience(vetRequest.getYearsOfExperience());
        val vetData = vetService.save(currentVet.get());
        stepStore.setItem("vetData", vetData);
    }
}
