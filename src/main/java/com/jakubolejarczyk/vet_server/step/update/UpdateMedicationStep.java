package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.model.dependent.Medication;
import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.service.dependent.MedicationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateMedicationStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final MedicationService medicationService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("medicationRequest")) throw new Error("The medicationRequest is required!");
        val medicationRequest = stepStore.getItem("medicationRequest", Medication.class);
        val medicationId = medicationRequest.getId();
        val currentMedication = medicationService.findById(medicationId);
        if (currentMedication.isEmpty()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("Failed to update medication!");
            return;
        }
        currentMedication.get().setFullName(medicationRequest.getFullName());
        currentMedication.get().setDescription(medicationRequest.getDescription());
        currentMedication.get().setManufacturer(medicationRequest.getManufacturer());
        currentMedication.get().setDose(medicationRequest.getDose());
        currentMedication.get().setQuantityInStock(medicationRequest.getQuantityInStock());
        currentMedication.get().setExpirationDate(medicationRequest.getExpirationDate());
        currentMedication.get().setPrice(medicationRequest.getPrice());
        currentMedication.get().setIsAvailable(medicationRequest.getIsAvailable());
        currentMedication.get().setClinicId(medicationRequest.getClinicId());
        val medicationData = medicationService.save(currentMedication.get());
        stepStore.setItem("medicationData", medicationData);
    }
}
