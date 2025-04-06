package com.jakubolejarczyk.vet_server.step.response.guard;

import com.jakubolejarczyk.vet_server.dto.data.guard.GetAccountData;
import com.jakubolejarczyk.vet_server.dto.metadata.guard.GetAccountMetadata;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAccountResponseStep implements StepRunnerModel<GetAccountData, GetAccountMetadata> {
    @Override
    public void runStep(StepStore<GetAccountData, GetAccountMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("accountData")) throw new Error("The accountData is required!");
            val accountData = stepStore.getItem("accountData", Account.class);
            val data = new GetAccountData(
                accountData.getEmail(),
                accountData.getFirstName(),
                accountData.getLastName(),
                accountData.getRole(),
                accountData.getPictureUrl()
            );
            val metadata = new GetAccountMetadata();
            stepStore.addMessage("Account details were downloaded successfully");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}
