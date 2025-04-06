//package com.jakubolejarczyk.vet_server.step.response.dependent.pet;
//
//import com.jakubolejarczyk.vet_server.dto.data.dependent.PetData;
//import com.jakubolejarczyk.vet_server.dto.metadata.dependent.PetMetadata;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class PetUpdateResponseStep implements StepRunnerModel<PetData, PetMetadata> {
//    @Override
//    public void runStep(StepStore<PetData, PetMetadata> stepStore) {
//        if (stepStore.getSuccess()) {
//            stepStore.addMessage("The pet has been updated successfully!");
//        }
//    }
//}
