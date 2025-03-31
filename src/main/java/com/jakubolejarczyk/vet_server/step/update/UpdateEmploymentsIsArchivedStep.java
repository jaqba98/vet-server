package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateEmploymentsIsArchivedStep implements StepModel {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("employments")) throw new Error("The employments is required!");
        val employmentsIds = stepStore.getItemAsArray("employments", Employment.class).stream()
                        .map(Employment::getId)
                        .toList();
        employmentService.updateIsArchived(employmentsIds, true);
        stepStore.addMessage("The employments have been archived successfully!");
    }
}
