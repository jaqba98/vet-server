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
public class UpdateVetStepRunner implements StepRunnerModel {
    private final VetService serviceClinicService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestVet")) throw new Error("The requestVet is required!");
        val requestVet = stepStore.getItem("requestVet", Vet.class);
        val vetId = requestVet.getId();
        val currentVet = serviceClinicService.findById(vetId);
        if (currentVet.isPresent()) {
            val newVet = Vet.builder()
                    .id(currentVet.get().getId())
                    .licenseNumber(requestVet.getLicenseNumber())
                    .licenseIssueDate(requestVet.getLicenseIssueDate())
                    .licenseExpiryDate(requestVet.getLicenseExpiryDate())
                    .specialization(requestVet.getSpecialization())
                    .yearsOfExperience(requestVet.getYearsOfExperience())
                    .accountId(currentVet.get().getAccountId())
                    .openingHourId(currentVet.get().getOpeningHourId())
                    .build();
            val vet = serviceClinicService.save(newVet);
            stepStore.setItem("vet", vet);
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Failed to update a vet.");
    }
}
