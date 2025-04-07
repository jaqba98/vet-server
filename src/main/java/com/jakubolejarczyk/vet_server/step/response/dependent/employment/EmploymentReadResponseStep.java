package com.jakubolejarczyk.vet_server.step.response.dependent.employment;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.EmploymentData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.EmploymentMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmploymentReadResponseStep implements StepRunnerModel<EmploymentData, EmploymentMetadata> {
    @Override
    public void runStep(StepStore<EmploymentData, EmploymentMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("myClinicIds")) throw new Error("The myClinicIds is required!");
            if (stepStore.hasNotItem("employmentsData")) throw new Error("The employmentsData is required!");
            if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
            if (stepStore.hasNotItem("accountId")) throw new Error("The accountId is required!");
            val myClinicIds = stepStore.getItemAsArray("myClinicIds", Long.class);
            val employmentsData = stepStore.getItemAsArray("employmentsData", Employment.class);
            val clinicId = stepStore.getItem("clinicId", BaseMetadata.class);
            val accountId = stepStore.getItem("accountId", BaseMetadata.class);
            val data = new EmploymentData(employmentsData);
            val metadata = new EmploymentMetadata(accountId, clinicId, myClinicIds);
            stepStore.addMessage("Employments were read correctly!");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}

