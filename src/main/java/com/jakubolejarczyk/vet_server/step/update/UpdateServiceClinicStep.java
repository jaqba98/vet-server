package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import com.jakubolejarczyk.vet_server.service.dependent.ServiceClinicService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateServiceClinicStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final ServiceClinicService serviceClinicService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("serviceClinicRequest")) throw new Error("The serviceClinicRequest is required!");
        val serviceClinicRequest = stepStore.getItem("serviceClinicRequest", ServiceClinic.class);
        val serviceClinicId = serviceClinicRequest.getId();
        val currentServiceClinic = serviceClinicService.findById(serviceClinicId);
        if (currentServiceClinic.isEmpty()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("Failed to update service clinic!");
            return;
        }
        currentServiceClinic.get().setFullName(serviceClinicRequest.getFullName());
        currentServiceClinic.get().setDescription(serviceClinicRequest.getDescription());
        currentServiceClinic.get().setCategory(serviceClinicRequest.getCategory());
        currentServiceClinic.get().setDurationMinutes(serviceClinicRequest.getDurationMinutes());
        currentServiceClinic.get().setPrice(serviceClinicRequest.getPrice());
        currentServiceClinic.get().setIsAvailable(serviceClinicRequest.getIsAvailable());
        currentServiceClinic.get().setClinicId(serviceClinicRequest.getClinicId());
        val serviceClinicData = serviceClinicService.save(currentServiceClinic.get());
        stepStore.setItem("serviceClinicData", serviceClinicData);
    }
}
