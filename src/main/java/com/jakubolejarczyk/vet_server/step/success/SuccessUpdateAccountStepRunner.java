package com.jakubolejarczyk.vet_server.step.success;

import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SuccessUpdateAccountStepRunner implements StepRunnerModel {
    @Override
    public void runStep(StepStore stepStore) {
        stepStore.addMessage("The account has been updated successfully!");
    }
}
