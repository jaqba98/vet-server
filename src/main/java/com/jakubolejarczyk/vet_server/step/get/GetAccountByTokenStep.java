// done
package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.security.TokenService;
import com.jakubolejarczyk.vet_server.service.independent.AccountService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAccountByTokenStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final AccountService accountService;
    private final TokenService tokenService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("token")) throw new Error("The token is required!");
        val token = stepStore.getItem("token", String.class);
        if (token != null) {
            val email = tokenService.decode(token);
            val account = accountService.findByEmail(email);
            if (account.isPresent()) {
                // Data
                stepStore.setItem("accountData", account.get());
                // MetaData
                val accountMetaData = new BaseMetadata();
                accountMetaData.addValue(account.get().getId(), account.get().getFullName());
                stepStore.setItem("accountMetaData", accountMetaData);
                return;
            }
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Failed to read account details. Account does not exist!");
    }
}
