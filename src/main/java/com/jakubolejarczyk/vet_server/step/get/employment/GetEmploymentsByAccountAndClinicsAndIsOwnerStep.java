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
public class GetEmploymentsByAccountAndClinicsAndIsOwnerStep<TData, TMetadata>
    implements StepRunnerModel<TData, TMetadata> {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("accountData")) throw new Error("The accountData is required!");
        if (stepStore.hasNotItem("clinicIds")) throw new Error("The clinicIds is required!");
        val accountData = stepStore.getItem("accountData", Account.class);
        val clinicIds = stepStore.getItemAsArray("clinicIds", Long.class);
        val accountId = accountData.getId();
        val employmentsData = employmentService.findAllByAccountIdAndClinicIdInAndIsOwnerTrue(accountId, clinicIds);
        stepStore.setItem("employmentsData", employmentsData);
    }
}
