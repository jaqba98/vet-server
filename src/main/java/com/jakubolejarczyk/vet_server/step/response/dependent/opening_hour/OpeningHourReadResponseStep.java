package com.jakubolejarczyk.vet_server.step.response.dependent.opening_hour;

import com.jakubolejarczyk.vet_server.dto.metadata.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.OpeningHourData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.OpeningHourMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.OpeningHour;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OpeningHourReadResponseStep implements StepRunnerModel<OpeningHourData, OpeningHourMetadata> {
    @Override
    public void runStep(StepStore<OpeningHourData, OpeningHourMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("openingHoursData")) throw new Error("The openingHoursData is required!");
            if (stepStore.hasNotItem("myOpeningHourIds")) throw new Error("The myOpeningHourIds is required!");
            if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
            val openingHoursData = stepStore.getItemAsArray("openingHoursData", OpeningHour.class);
            val myOpeningHourIds = stepStore.getItemAsArray("myOpeningHourIds", Long.class);
            val clinicId = stepStore.getItem("clinicId", BaseMetadata.class);
            val data = new OpeningHourData(openingHoursData);
            val metadata = new OpeningHourMetadata(myOpeningHourIds, clinicId);
            stepStore.addMessage("Opening hours were read correctly!");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}
