package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import com.jakubolejarczyk.vet_server.service.dependent.VetService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class CreateVetServiceStep implements StepModel {
    private final VetService vetService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestVetService")) throw new Error("The requestVetService is required!");
        if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
        val requestVetService = stepStore.getItem("requestVetService", ServiceClinic.class);
        val clinicId = stepStore.getItem("clinicId", Long.class);
        val vetService = ServiceClinic.builder()
                .isArchived(false)
                .entityName(requestVetService.getEntityName())
                .description(requestVetService.getDescription())
                .category(requestVetService.getCategory())
                .durationMinutes(requestVetService.getDurationMinutes())
                .price(requestVetService.getPrice())
                .isAvailable(requestVetService.getIsAvailable())
                .clinicId(clinicId)
                .build();
        this.vetService.create(vetService);
    }
}
