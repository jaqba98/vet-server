package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.service.dependent.MedicationService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateMedicationIsArchivedStep implements StepModel {
    private final MedicationService medicationService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("medicationIds")) throw new Error("The medicationIds is required!");
        val medicationIds = stepStore.getItemAsArray("medicationIds", Long.class);
        medicationService.updateIsArchived(medicationIds, true);
    }
}
