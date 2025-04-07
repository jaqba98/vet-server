package com.jakubolejarczyk.vet_server.step.response.dependent.service_clinic;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ServiceClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ServiceClinicMetadata;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceClinicDeleteResponseStep implements StepRunnerModel<ServiceClinicData, ServiceClinicMetadata> {
    @Override
    public void runStep(StepStore<ServiceClinicData, ServiceClinicMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            stepStore.addMessage("The service clinic was deleted correctly!");
        }
    }
}
