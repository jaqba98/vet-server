package com.jakubolejarczyk.vet_server.service.step.check;

import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckAccountHasRoleStep implements StepModel {
    @Override
    public void runStep(StepStore stepStore) {
        val role = (String) stepStore.getItem("role");
        val success = role != null;
        val message = success ? "The Account has role set" : "Account has not role set!";
        stepStore.setSuccess(success);
        stepStore.addMessage(message);
    }
}
