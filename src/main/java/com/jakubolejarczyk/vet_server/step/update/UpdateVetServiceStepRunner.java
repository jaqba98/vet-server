package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import com.jakubolejarczyk.vet_server.service.dependent.ServiceClinicService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class UpdateVetServiceStepRunner implements StepRunnerModel {
    private final ServiceClinicService vetService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestVetService")) throw new Error("The requestVetService is required!");
        val requestVetService = stepStore.getItem("requestVetService", ServiceClinic.class);
        val vetServiceId = requestVetService.getId();
        val currentVetService = vetService.findById(vetServiceId);
        if (currentVetService.isPresent()) {
            val newVetService = ServiceClinic.builder()
                    .id(currentVetService.get().getId())
                    .entityName(requestVetService.getEntityName())
                    .description(requestVetService.getDescription())
                    .category(requestVetService.getCategory())
                    .durationMinutes(requestVetService.getDurationMinutes())
                    .price(requestVetService.getPrice())
                    .isAvailable(requestVetService.getIsAvailable())
                    .clinicId(currentVetService.get().getClinicId())
                    .build();
            val vetService = this.vetService.save(newVetService);
            stepStore.setItem("vetService", vetService);
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Failed to update a vet service.");
    }
}
