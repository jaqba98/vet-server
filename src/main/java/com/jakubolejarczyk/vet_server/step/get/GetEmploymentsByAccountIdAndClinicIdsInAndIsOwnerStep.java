package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetEmploymentsByAccountIdAndClinicIdsInAndIsOwnerStep implements StepModel {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
        if (stepStore.hasNotItem("ids")) throw new Error("The ids is required!");
        val account = stepStore.getItem("account", Account.class);
        val accountId = account.getId();
        val ids = stepStore.getItemAsArray("ids", Long.class);
        val employments = employmentService.findAllByAccountIdAndClinicIdsInAndIsOwner(accountId, ids);
        stepStore.setItem("employments", employments);
    }
}
