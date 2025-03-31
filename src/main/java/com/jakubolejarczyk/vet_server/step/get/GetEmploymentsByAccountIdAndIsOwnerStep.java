package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetEmploymentsByAccountIdAndIsOwnerStep implements StepModel {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
        val account = stepStore.getItem("account", Account.class);
        val accountId = account.getId();
        val employments = employmentService.findAllByAccountIdAndIsOwner(accountId);
        stepStore.setItem("employments", employments);
    }
}
