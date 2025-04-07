package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import com.jakubolejarczyk.vet_server.service.dependent.ServiceClinicService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateServiceClinicStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final ServiceClinicService serviceClinicService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("serviceClinicRequest")) throw new Error("The serviceClinicRequest is required!");
        if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
        val serviceClinicRequest = stepStore.getItem("serviceClinicRequest", ServiceClinic.class);
        val clinicId = stepStore.getItem("clinicId", Long.class);
        val serviceClinic = ServiceClinic.builder()
            .fullName(serviceClinicRequest.getFullName())
            .description(serviceClinicRequest.getDescription())
            .category(serviceClinicRequest.getCategory())
            .durationMinutes(serviceClinicRequest.getDurationMinutes())
            .price(serviceClinicRequest.getPrice())
            .isAvailable(serviceClinicRequest.getIsAvailable())
            .clinicId(clinicId)
            .build();
        serviceClinicService.save(serviceClinic);
    }
}
