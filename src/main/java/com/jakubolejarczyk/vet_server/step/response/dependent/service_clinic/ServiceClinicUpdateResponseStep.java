package com.jakubolejarczyk.vet_server.step.response.dependent.service_clinic;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ServiceClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ServiceClinicMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class ServiceClinicUpdateResponseStep implements StepRunnerModel<ServiceClinicData, ServiceClinicMetadata> {
    @Override
    public void runStep(StepStore<ServiceClinicData, ServiceClinicMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("serviceClinicData")) throw new Error("The serviceClinicData is required!");
            val serviceClinicData = stepStore.getItem("serviceClinicData", ServiceClinic.class);
            val data = new ServiceClinicData(Collections.singletonList(serviceClinicData));
            stepStore.addMessage("Service clinic was updated correctly!");
            stepStore.setData(data);
        } else {
            if (stepStore.hasNotItem("serviceClinicRequest")) throw new Error("The serviceClinicRequest is required!");
            val serviceClinicRequest = stepStore.getItem("serviceClinicRequest", ServiceClinic.class);
            val data = new ServiceClinicData(Collections.singletonList(serviceClinicRequest));
            stepStore.setData(data);
        }
    }
}
