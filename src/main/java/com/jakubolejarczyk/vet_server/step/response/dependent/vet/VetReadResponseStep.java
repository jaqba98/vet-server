package com.jakubolejarczyk.vet_server.step.response.dependent.vet;

import com.jakubolejarczyk.vet_server.dto.metadata.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.VetData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.VetMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class VetReadResponseStep implements StepRunnerModel<VetData, VetMetadata> {
    @Override
    public void runStep(StepStore<VetData, VetMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("vetData")) throw new Error("The vetData is required!");
            if (stepStore.hasNotItem("accountId")) throw new Error("The accountId is required!");
            val vetData = stepStore.getItem("vetData", Vet.class);
            val accountId = stepStore.getItem("accountId", BaseMetadata.class);
            val data = new VetData(Collections.singletonList(vetData));
            val metadata = new VetMetadata(accountId);
            stepStore.addMessage("Vet was read correctly!");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}
