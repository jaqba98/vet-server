package com.jakubolejarczyk.vet_server.step.check;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckAccountPermissionToClinicStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
        if (stepStore.hasNotItem("accountData")) throw new Error("The accountData is required!");
        val clinicId = stepStore.getItem("clinicId", Long.class);
        val accountData = stepStore.getItem("accountData", Account.class);
        val accountId = accountData.getId();
        val employment = employmentService.findByAccountIdAndClinicId(accountId, clinicId);
        if (employment.isPresent()) return;
        stepStore.setSuccess(false);
        stepStore.addMessage("You do not have permission to the clinic!");
    }
}
