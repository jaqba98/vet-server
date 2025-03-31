package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetClinicIdsForAccountStep implements StepModel {
    private final EmploymentService employmentService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("accountId")) throw new Error("The accountId is required!");
        val accountId = stepStore.getItem("accountId", Long.class);
        val clinicIds = employmentService.findAllByAccountId(accountId).stream()
                .map(Employment::getClinicId)
                .toList();
        stepStore.setItem("clinicIds", clinicIds);
    }
}
