package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Medication;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.dependent.MedicationService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateMedicationStep implements StepModel {
    private final MedicationService medicationService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestMedication")) throw new Error("The requestMedication is required!");
        val requestMedication = stepStore.getItem("requestMedication", Medication.class);
        val medicationId = requestMedication.getId();
        val currentMedication = medicationService.findById(medicationId);
        if (currentMedication.isPresent()) {
            val newMedication = Medication.builder()
                    .id(currentMedication.get().getId())
                    .isArchived(currentMedication.get().getIsArchived())
                    .name(requestMedication.getName())
                    .description(requestMedication.getDescription())
                    .manufacturer(requestMedication.getManufacturer())
                    .dose(requestMedication.getDose())
                    .quantityInStock(requestMedication.getQuantityInStock())
                    .expirationDate(requestMedication.getExpirationDate())
                    .price(requestMedication.getPrice())
                    .isAvailable(requestMedication.getIsAvailable())
                    .clinicId(currentMedication.get().getClinicId())
                    .build();
            val medication = medicationService.create(newMedication);
            stepStore.setItem("medication", medication);
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Failed to update a medication.");
    }
}
