package com.jakubolejarczyk.vet_server.step.response.dependent.opening_hour;

import com.jakubolejarczyk.vet_server.dto.data.dependent.OpeningHourData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.OpeningHourMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.OpeningHour;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class OpeningHourUpdateResponseStep implements StepRunnerModel<OpeningHourData, OpeningHourMetadata> {
    @Override
    public void runStep(StepStore<OpeningHourData, OpeningHourMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("openingHourData")) throw new Error("The openingHourData is required!");
            val openingHourData = stepStore.getItem("openingHourData", OpeningHour.class);
            val data = new OpeningHourData(Collections.singletonList(openingHourData));
            stepStore.addMessage("Opening hours were updated correctly!");
            stepStore.setData(data);
        } else {
            if (stepStore.hasNotItem("openingHourRequest")) throw new Error("The openingHourRequest is required!");
            val openingHourRequest = stepStore.getItem("openingHourRequest", OpeningHour.class);
            val data = new OpeningHourData(Collections.singletonList(openingHourRequest));
            stepStore.setData(data);
        }
    }
}
