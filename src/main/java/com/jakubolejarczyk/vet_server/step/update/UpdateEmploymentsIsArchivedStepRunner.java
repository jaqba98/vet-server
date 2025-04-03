package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateEmploymentsIsArchivedStepRunner implements StepRunnerModel {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("employments")) throw new Error("The employments is required!");
        val employments = stepStore.getItemAsArray("employments", Employment.class);
        val employmentsIds = employments.stream()
                .map(Employment::getId)
                .toList();
        employmentService.deleteAllById(employmentsIds);
    }
}
