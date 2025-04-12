package com.jakubolejarczyk.vet_server.step.metadata;

import com.jakubolejarczyk.vet_server.dto.metadata.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.EmploymentData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.EmploymentMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.independent.ClinicService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmploymentMetadataStep implements StepRunnerModel<EmploymentData, EmploymentMetadata> {
    private final AccountService accountService;
    private final ClinicService clinicService;

    @Override
    public void runStep(StepStore<EmploymentData, EmploymentMetadata> stepStore) {
        if (stepStore.hasNotItem("employmentsData")) throw new Error("The employmentsData is required!");
        val employmentsData = stepStore.getItemAsArray("employmentsData", Employment.class);
        val myClinicIds = employmentsData.stream()
            .filter(Employment::getIsOwner)
            .map(Employment::getClinicId)
            .distinct()
            .toList();
        stepStore.setItem("myClinicIds", myClinicIds);
        val clinicIds = employmentsData.stream()
            .map(Employment::getClinicId)
            .distinct()
            .toList();
        val clinicId = new BaseMetadata();
        clinicService.findAllById(clinicIds).forEach(((clinic) -> clinicId.addValue(clinic.getId(), clinic.getFullName())));
        stepStore.setItem("clinicId", clinicId);
        val accountId = new BaseMetadata();
        accountService.findAll().forEach((account) -> {
            val fullName = account.getFirstName() + " " + account.getLastName();
            accountId.addValue(account.getId(), fullName);
        });
        stepStore.setItem("accountId", accountId);
    }
}
