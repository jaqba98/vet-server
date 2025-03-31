package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateEmploymentStep implements StepModel {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
        if (stepStore.hasNotItem("clinic")) throw new Error("The clinic is required!");
        val account = stepStore.getItem("account", Account.class);
        val clinic = stepStore.getItem("clinic", Clinic.class);
        val employment = Employment.builder()
                .isOwner(true)
                .isArchived(false)
                .accountId(account.getId())
                .clinicId(clinic.getId())
                .build();
        val newEmployment = employmentService.create(employment);
        stepStore.setItem("employment", newEmployment);
    }
}
