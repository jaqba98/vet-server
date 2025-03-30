package com.jakubolejarczyk.vet_server.step.check;

import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckAccountHasRoleStep implements StepModel {
    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("role")) throw new Error("The role is required!");
        val role = (String) stepStore.getItem("role");
        if (role == null) {
            stepStore.setSuccess(false);
            stepStore.addMessage("Account has not role set!");
        }
    }
}
