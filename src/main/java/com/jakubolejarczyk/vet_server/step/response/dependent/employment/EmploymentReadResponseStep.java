//package com.jakubolejarczyk.vet_server.step.response.dependent.employment;
//
//import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
//import com.jakubolejarczyk.vet_server.dto.data.dependent.EmploymentData;
//import com.jakubolejarczyk.vet_server.dto.metadata.dependent.EmploymentMetadata;
//import com.jakubolejarczyk.vet_server.model.dependent.Employment;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class EmploymentReadResponseStep implements StepRunnerModel<EmploymentData, EmploymentMetadata> {
//    @Override
//    public void runStep(StepStore<EmploymentData, EmploymentMetadata> stepStore) {
//        if (stepStore.getSuccess()) {
//            if (stepStore.hasNotItem("employments")) throw new Error("The employments is required!");
//            if (stepStore.hasNotItem("accountIdMetadata")) throw new Error("The accountIdMetadata is required!");
//            if (stepStore.hasNotItem("clinicIdMetaData")) throw new Error("The clinicIdMetaData is required!");
//            val employments = stepStore.getItemAsArray("employments", Employment.class);
//            val accountIdMetadata = stepStore.getItem("accountIdMetadata", BaseMetadata.class);
//            val clinicIdMetaData = stepStore.getItem("clinicIdMetaData", BaseMetadata.class);
//            val data = new EmploymentData(employments);
//            val metadata = new EmploymentMetadata(accountIdMetadata, clinicIdMetaData);
//            stepStore.addMessage("Employments were downloaded successfully");
//            stepStore.setData(data);
//            stepStore.setMetadata(metadata);
//        }
//    }
//}
