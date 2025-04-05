package com.jakubolejarczyk.vet_server.step.check;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckAccountPermissionToClientStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
        if (stepStore.hasNotItem("client")) throw new Error("The client is required!");
        val account = stepStore.getItem("account", Account.class);
        val client = stepStore.getItem("client", Client.class);
        val employment = employmentService.findByAccountIdAndClinicId(account.getId(), client.getClinicId());
        if (employment.isPresent()) return;
        stepStore.setSuccess(false);
        stepStore.addMessage("You do not have permission to the client!");
    }
}
