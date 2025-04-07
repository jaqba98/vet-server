package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.Medication;
import com.jakubolejarczyk.vet_server.service.dependent.MedicationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateMedicationStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final MedicationService medicationService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("medicationRequest")) throw new Error("The medicationRequest is required!");
        if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
        val medicationRequest = stepStore.getItem("medicationRequest", Medication.class);
        val clinicId = stepStore.getItem("clinicId", Long.class);
        val medication = Medication.builder()
                .fullName(medicationRequest.getFullName())
                .description(medicationRequest.getDescription())
                .manufacturer(medicationRequest.getManufacturer())
                .dose(medicationRequest.getDose())
                .quantityInStock(medicationRequest.getQuantityInStock())
                .expirationDate(medicationRequest.getExpirationDate())
                .price(medicationRequest.getPrice())
                .isAvailable(medicationRequest.getIsAvailable())
                .clinicId(clinicId)
                .build();
        medicationService.save(medication);
    }
}
