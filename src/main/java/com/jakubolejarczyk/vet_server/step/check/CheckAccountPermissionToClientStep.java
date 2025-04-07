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
        if (stepStore.hasNotItem("accountData")) throw new Error("The accountData is required!");
        if (stepStore.hasNotItem("clientData")) throw new Error("The clientData is required!");
        val accountData = stepStore.getItem("accountData", Account.class);
        val clientData = stepStore.getItem("clientData", Client.class);
        val employment = employmentService.findByAccountIdAndClinicId(accountData.getId(), clientData.getClinicId());
        if (employment.isPresent()) return;
        stepStore.setSuccess(false);
        stepStore.addMessage("You do not have permission to the client!");
    }
}
