package com.jakubolejarczyk.vet_server.step.check;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckAccountHasRoleStepRunner implements StepRunnerModel {
    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
        val account = stepStore.getItem("account", Account.class);
        if (account.getRole() == null) {
            stepStore.setSuccess(false);
            stepStore.addMessage("Account has not role set!");
        }
    }
}
