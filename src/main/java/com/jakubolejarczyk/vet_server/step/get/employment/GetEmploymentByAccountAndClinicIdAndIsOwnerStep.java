package com.jakubolejarczyk.vet_server.step.get.employment;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetEmploymentByAccountAndClinicIdAndIsOwnerStep<TData, TMetadata>
    implements StepRunnerModel<TData, TMetadata> {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("accountData")) throw new Error("The accountData is required!");
        if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
        val accountData = stepStore.getItem("accountData", Account.class);
        val clinicId = stepStore.getItem("clinicId", Long.class);
        val accountId = accountData.getId();
        val employmentData = employmentService.findByAccountIdAndClinicIdAndIsOwnerTrue(accountId, clinicId);
        if (employmentData.isPresent()) {
            stepStore.setItem("employmentData", employmentData.get());
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Failed to get employment!");
    }
}
