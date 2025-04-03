package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.service.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetClinicStepRunner implements StepRunnerModel {
    private final ClinicService clinicService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
        val clinicId = stepStore.getItem("clinicId", Long.class);
        val currentClinic = clinicService.findById(clinicId);
        if (currentClinic.isPresent()) {
            stepStore.setItem("currentClinic", currentClinic.get());
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("The clinic does not exist!");
    }
}
