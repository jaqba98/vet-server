package com.jakubolejarczyk.vet_server.step.set;

import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.dependent.VetService;
import com.jakubolejarczyk.vet_server.service.independent.AccountService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SetAccountRoleStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final AccountService accountService;
    private final VetService vetService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("role")) throw new Error("The role is required!");
        if (stepStore.hasNotItem("accountData")) throw new Error("The accountData is required!");
        val role = stepStore.getItem("role", String.class);
        val accountData = stepStore.getItem("accountData", Account.class);
        if (role.equals("vet")) {
            createVet(accountData);
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Role not set correctly!");
    }

    private void createVet(Account accountData) {
        val accountId = accountData.getId();
        val vet = vetService.findByAccountId(accountId);
        if (vet.isPresent()) return;
        Vet newVet = Vet.builder()
            .accountId(accountId)
            .build();
        vetService.save(newVet);
        accountService.updateRoleByEmail(accountData.getEmail(), "vet");
    }
}
