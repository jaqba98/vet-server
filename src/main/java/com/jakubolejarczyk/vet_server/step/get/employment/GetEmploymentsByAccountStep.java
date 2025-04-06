package com.jakubolejarczyk.vet_server.step.get.employment;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.service.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.independent.ClinicService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetEmploymentsByAccountStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final EmploymentService employmentService;
    private final AccountService accountService;
    private final ClinicService clinicService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("accountData")) throw new Error("The accountData is required!");
        val accountData = stepStore.getItem("accountData", Account.class);
        val accountId = accountData.getId();
        val employmentsData = employmentService.findAllByAccountId(accountId);
        // Data
        stepStore.setItem("employmentsData", employmentsData);
        // MetaData account id
        val employmentsAccountIdMetaData = new BaseMetadata();
        val accountIds = employmentsData.stream()
            .map(Employment::getAccountId)
            .toList();
        accountService.findAllById(accountIds).forEach(account -> {
            employmentsAccountIdMetaData.addValue(account.getId(), account.getFullName());
        });
        stepStore.setItem("employmentsAccountIdMetaData", employmentsAccountIdMetaData);
        // MetaData clinic id
        val employmentsClinicIdMetaData = new BaseMetadata();
        val clinicIds = employmentsData.stream()
            .map(Employment::getClinicId)
            .toList();
        clinicService.findAllById(clinicIds).forEach(clinic -> {
            employmentsClinicIdMetaData.addValue(clinic.getId(), clinic.getFullName());
        });
        stepStore.setItem("employmentsClinicIdMetaData", employmentsClinicIdMetaData);
    }
}
