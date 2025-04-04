package com.jakubolejarczyk.vet_server.step.response.logic;

import com.jakubolejarczyk.vet_server.dto.data.logic.LogoutData;
import com.jakubolejarczyk.vet_server.dto.metadata.logic.LogoutMetadata;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogoutResponseStep implements StepRunnerModel<LogoutData, LogoutMetadata> {
    @Override
    public void runStep(StepStore<LogoutData, LogoutMetadata> stepStore) {
        stepStore.addMessage("You have logged out successfully!");
    }
}
