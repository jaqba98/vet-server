package com.jakubolejarczyk.vet_server.step.metadata;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.VetData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.VetMetadata;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VetMetadataStep implements StepRunnerModel<VetData, VetMetadata> {
    @Override
    public void runStep(StepStore<VetData, VetMetadata> stepStore) {
        if (stepStore.hasNotItem("accountData")) throw new Error("The accountData is required!");
        val accountData = stepStore.getItem("accountData", Account.class);
        val accountId = new BaseMetadata();
        val fullName = accountData.getFirstName() + " " + accountData.getLastName();
        accountId.addValue(accountData.getId(), fullName);
        stepStore.setItem("accountId", accountId);
    }
}
