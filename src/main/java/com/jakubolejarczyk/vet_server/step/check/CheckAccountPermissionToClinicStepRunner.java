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
public class CheckAccountPermissionToClinicStepRunner implements StepRunnerModel {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
        val clinicId = stepStore.getItem("clinicId", Long.class);
        val account = stepStore.getItem("account", Account.class);
        val accountId = account.getId();
        val employment = employmentService.findByAccountIdAndClinicIdAndIsOwner(accountId, clinicId);
        if (employment.isPresent()) {
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("You do not have permission to the clinic!");
    }
}
