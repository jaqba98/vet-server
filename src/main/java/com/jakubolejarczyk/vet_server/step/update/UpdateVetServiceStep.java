package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Service;
import com.jakubolejarczyk.vet_server.service.dependent.VetServiceService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class UpdateVetServiceStep implements StepModel {
    private final VetServiceService vetServiceService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestVetService")) throw new Error("The requestVetService is required!");
        val requestVetService = stepStore.getItem("requestVetService", Service.class);
        val vetServiceId = requestVetService.getId();
        val currentVetService = vetServiceService.findById(vetServiceId);
        if (currentVetService.isPresent()) {
            val newVetService = Service.builder()
                    .id(currentVetService.get().getId())
                    .isArchived(currentVetService.get().getIsArchived())
                    .name(requestVetService.getName())
                    .description(requestVetService.getDescription())
                    .category(requestVetService.getCategory())
                    .durationMinutes(requestVetService.getDurationMinutes())
                    .price(requestVetService.getPrice())
                    .isAvailable(requestVetService.getIsAvailable())
                    .clinicId(currentVetService.get().getClinicId())
                    .build();
            val vetService = vetServiceService.create(newVetService);
            stepStore.setItem("vetService", vetService);
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Failed to update a vet service.");
    }
}
