package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.service.independent.AccountService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetEmploymentsByAccountIdStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final AccountService accountService;
    private final EmploymentService employmentService;
    private final ClinicService clinicService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
        val account = stepStore.getItem("account", Account.class);
        // Data
        val accountId = account.getId();
        val employments = employmentService.findAllByAccountId(accountId);
        stepStore.setItem("employments", employments);
        // MetaData Account
        val employmentsAccountIdMetadata = new BaseMetadata();
        val accountIds = employments.stream()
            .map(Employment::getAccountId)
            .distinct()
            .toList();
        accountService.findAllById(accountIds).forEach((accountById) -> {
            employmentsAccountIdMetadata.addValue(accountById.getId(), accountById.getFullName());
        });
        stepStore.setItem("employmentsAccountIdMetadata", employmentsAccountIdMetadata);
        // MetaData Clinic
        val employmentsClinicIdMetadata = new BaseMetadata();
        val clinicIds = employments.stream()
            .map(Employment::getClinicId)
            .distinct()
            .toList();
        clinicService.findAllById(clinicIds).forEach((clinicById) -> {
            employmentsClinicIdMetadata.addValue(clinicById.getId(), clinicById.getFullName());
        });
        stepStore.setItem("employmentsClinicIdMetadata", employmentsClinicIdMetadata);
    }
}
