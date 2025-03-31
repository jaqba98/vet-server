package com.jakubolejarczyk.vet_server.step.check;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckAccountPermissionToClinicStep implements StepModel {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("newClinic")) throw new Error("The newClinic is required!");
        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
        val newClinic = stepStore.getItem("newClinic", Clinic.class);
        val account = stepStore.getItem("account", Account.class);
        val newClinicId = newClinic.getId();
        val accountId = account.getId();
        val employment = employmentService.findByClinicIdAndAccountIdAndIsOwner(newClinicId, accountId);
        if (employment.isPresent()) {
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("You do not have permission to the clinic!");
    }
}
