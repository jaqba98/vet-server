package com.jakubolejarczyk.vet_server.step.response.independent;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ClinicMetadata;
import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class ClinicUpdateResponseStep implements StepRunnerModel<ClinicData, ClinicMetadata> {
    @Override
    public void runStep(StepStore<ClinicData, ClinicMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("clinicData")) throw new Error("The clinicData is required!");
            val clinicData = stepStore.getItem("clinicData", Clinic.class);
            val data = new ClinicData(Collections.singletonList(clinicData));
            stepStore.addMessage("Clinic were updated correctly!");
            stepStore.setData(data);
        }
    }
}
