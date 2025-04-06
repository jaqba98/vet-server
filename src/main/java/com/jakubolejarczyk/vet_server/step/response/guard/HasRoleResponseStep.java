//package com.jakubolejarczyk.vet_server.step.response.guard;
//
//import com.jakubolejarczyk.vet_server.dto.data.guard.HasRoleData;
//import com.jakubolejarczyk.vet_server.dto.metadata.guard.HasRoleMetadata;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class HasRoleResponseStep implements StepRunnerModel<HasRoleData, HasRoleMetadata> {
//    @Override
//    public void runStep(StepStore<HasRoleData, HasRoleMetadata> stepStore) {
//        if (stepStore.getSuccess()) {
//            val data = new HasRoleData();
//            val metadata = new HasRoleMetadata();
//            stepStore.addMessage("The account has a role");
//            stepStore.setData(data);
//            stepStore.setMetadata(metadata);
//        }
//    }
//}
