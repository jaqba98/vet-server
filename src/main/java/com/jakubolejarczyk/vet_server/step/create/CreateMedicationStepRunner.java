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
public class CreateMedicationStepRunner implements StepRunnerModel {
    private final MedicationService medicationService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestMedication")) throw new Error("The requestMedication is required!");
        if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
        val requestMedication = stepStore.getItem("requestMedication", Medication.class);
        val clinicId = stepStore.getItem("clinicId", Long.class);
        val medication = Medication.builder()
                .entityName(requestMedication.getEntityName())
                .description(requestMedication.getDescription())
                .manufacturer(requestMedication.getManufacturer())
                .dose(requestMedication.getDose())
                .quantityInStock(requestMedication.getQuantityInStock())
                .expirationDate(requestMedication.getExpirationDate())
                .price(requestMedication.getPrice())
                .isAvailable(requestMedication.getIsAvailable())
                .clinicId(clinicId)
                .build();
        medicationService.save(medication);
        stepStore.addMessage("Medication was created!");
    }
}
