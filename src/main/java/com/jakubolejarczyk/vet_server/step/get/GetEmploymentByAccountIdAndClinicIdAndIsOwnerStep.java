package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetEmploymentByAccountIdAndClinicIdAndIsOwnerStep implements StepModel {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
        if (stepStore.hasNotItem("requestClinic")) throw new Error("The requestClinic is required!");
        val account = stepStore.getItem("account", Account.class);
        val requestClinic = stepStore.getItem("requestClinic", Clinic.class);
        val accountId = account.getId();
        val clinicId = requestClinic.getId();
        val employment = employmentService.findByAccountIdAndClinicIdAndIsOwner(accountId, clinicId);
        if (employment.isPresent()) {
            stepStore.setItem("employment", employment.get());
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Failed to get employment!");
    }
}
