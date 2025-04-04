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
        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
        val account = stepStore.getItem("account", Account.class);
        if (stepStore.getSuccess()) {
            val data = GetAccountData.builder()
                .email(account.getEmail())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .role(account.getRole())
                .pictureUrl(account.getPictureUrl())
                .build();
            val metadata = GetAccountMetadata.builder().build();
            stepStore.addMessage("Account details were downloaded successfully");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}
