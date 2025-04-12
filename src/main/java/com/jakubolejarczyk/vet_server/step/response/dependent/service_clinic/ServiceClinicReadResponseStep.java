package com.jakubolejarczyk.vet_server.step.response.dependent.service_clinic;

import com.jakubolejarczyk.vet_server.dto.metadata.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.ServiceClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ServiceClinicMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceClinicReadResponseStep implements StepRunnerModel<ServiceClinicData, ServiceClinicMetadata> {
    @Override
    public void runStep(StepStore<ServiceClinicData, ServiceClinicMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("serviceClinicsData")) throw new Error("The serviceClinicsData is required!");
            if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
            val serviceClinicsData = stepStore.getItemAsArray("serviceClinicsData", ServiceClinic.class);
            val clinicId = stepStore.getItem("clinicId", BaseMetadata.class);
            val data = new ServiceClinicData(serviceClinicsData);
            val metaData = new ServiceClinicMetadata(clinicId);
            stepStore.addMessage("The service clinic has been read successfully!");
            stepStore.setData(data);
            stepStore.setMetadata(metaData);
        }
    }
}
