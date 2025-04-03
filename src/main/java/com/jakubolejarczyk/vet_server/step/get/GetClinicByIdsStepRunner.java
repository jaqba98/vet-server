package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.service.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetClinicByIdsStepRunner implements StepRunnerModel {
    private final ClinicService clinicService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("clinicIds")) throw new Error("The clinicIds is required!");
        val clinicIds = stepStore.getItemAsArray("clinicIds", Long.class);
        val clinics = clinicService.findAllById(clinicIds);
        stepStore.setItem("clinics", clinics);
    }
}
