package com.jakubolejarczyk.vet_server.step.check;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckAccountHasRoleStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("accountData")) throw new Error("The accountData is required!");
        val accountData = stepStore.getItem("accountData", Account.class);
        if (accountData.getRole() == null) {
            stepStore.setSuccess(false);
            stepStore.addMessage("Account has not role set!");
        }
    }
}
