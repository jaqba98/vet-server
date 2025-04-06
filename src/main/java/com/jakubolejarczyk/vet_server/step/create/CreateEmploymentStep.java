package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.service.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateEmploymentStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("accountData")) throw new Error("The accountData is required!");
        if (stepStore.hasNotItem("clinicData")) throw new Error("The clinicData is required!");
        val accountData = stepStore.getItem("accountData", Account.class);
        val clinicData = stepStore.getItem("clinicData", Clinic.class);
        val accountId = accountData.getId();
        val clinicId = clinicData.getId();
        val newEmployment = Employment.builder()
            .isOwner(true)
            .accountId(accountId)
            .clinicId(clinicId)
            .build();
        val employmentData = employmentService.save(newEmployment);
        stepStore.setItem("employmentData", employmentData);
    }
}
