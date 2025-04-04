package com.jakubolejarczyk.vet_server.step.response.guard;

import com.jakubolejarczyk.vet_server.dto.data.guard.HasRoleData;
import com.jakubolejarczyk.vet_server.dto.metadata.guard.HasRoleMetadata;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HasRoleResponseStep implements StepRunnerModel<HasRoleData, HasRoleMetadata> {
    @Override
    public void runStep(StepStore<HasRoleData, HasRoleMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            stepStore.addMessage("The account has a role");
        }
    }
}
