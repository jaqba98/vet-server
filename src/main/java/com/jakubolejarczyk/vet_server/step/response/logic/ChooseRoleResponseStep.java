package com.jakubolejarczyk.vet_server.step.response.logic;

import com.jakubolejarczyk.vet_server.dto.data.logic.ChooseRoleData;
import com.jakubolejarczyk.vet_server.dto.metadata.logic.ChooseRoleMetadata;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChooseRoleResponseStep implements StepRunnerModel<ChooseRoleData, ChooseRoleMetadata> {
    @Override
    public void runStep(StepStore<ChooseRoleData, ChooseRoleMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("role")) throw new Error("The role is required!");
            val role = stepStore.getItem("role", String.class);
            val data = new ChooseRoleData(role);
            val metadata = new ChooseRoleMetadata();
            stepStore.addMessage("The role is set correctly!");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}
