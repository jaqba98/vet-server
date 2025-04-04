package com.jakubolejarczyk.vet_server.step.check;

import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckPasswordsMatchStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("password")) throw new Error("The password is required!");
        if (stepStore.hasNotItem("confirmPassword")) throw new Error("The confirmPassword is required!");
        val password = stepStore.getItem("password", String.class);
        val confirmPassword = stepStore.getItem("confirmPassword", String.class);
        if (password.equals(confirmPassword)) return;
        stepStore.setSuccess(false);
        stepStore.addMessage("Password and confirmation password do not match!");
    }
}
