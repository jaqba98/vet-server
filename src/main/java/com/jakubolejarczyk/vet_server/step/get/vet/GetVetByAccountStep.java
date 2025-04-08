package com.jakubolejarczyk.vet_server.step.get.vet;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.dependent.VetService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetVetByAccountStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final VetService vetService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("accountData")) throw new Error("The accountData is required!");
        val accountData = stepStore.getItem("accountData", Account.class);
        val accountId = accountData.getId();
        val vetData = vetService.findByAccountId(accountId);
        if (vetData.isPresent()) {
            stepStore.setItem("vetData", vetData.get());
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Failed to read vet details. Vet does not exist!");
    }
}
