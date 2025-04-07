package com.jakubolejarczyk.vet_server.step.delete;

import com.jakubolejarczyk.vet_server.service.dependent.ServiceClinicService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteServiceClinicStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final ServiceClinicService serviceClinicService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("serviceClinicIds")) throw new Error("The serviceClinicIds is required!");
        val serviceClinicIds = stepStore.getItemAsArray("serviceClinicIds", Long.class);
        serviceClinicService.deleteAllById(serviceClinicIds);
    }
}
