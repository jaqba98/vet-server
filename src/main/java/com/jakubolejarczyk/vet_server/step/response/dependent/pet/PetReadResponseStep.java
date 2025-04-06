//package com.jakubolejarczyk.vet_server.step.response.dependent.pet;
//
//import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
//import com.jakubolejarczyk.vet_server.dto.data.dependent.PetData;
//import com.jakubolejarczyk.vet_server.dto.metadata.dependent.PetMetadata;
//import com.jakubolejarczyk.vet_server.model.dependent.Pet;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class PetReadResponseStep implements StepRunnerModel<PetData, PetMetadata> {
//    @Override
//    public void runStep(StepStore<PetData, PetMetadata> stepStore) {
//        if (stepStore.getSuccess()) {
//            if (stepStore.hasNotItem("pets")) throw new Error("The pets is required!");
//            if (stepStore.hasNotItem("clientIdMetaData")) throw new Error("The clientIdMetaData is required!");
//            val pets = stepStore.getItemAsArray("pets", Pet.class);
//            val clientIdMetaData = stepStore.getItem("clientIdMetaData", BaseMetadata.class);
//            val data = new PetData(pets);
//            val metaData = new PetMetadata(clientIdMetaData);
//            stepStore.addMessage("The pet has been read successfully!");
//            stepStore.setData(data);
//            stepStore.setMetadata(metaData);
//        }
//    }
//}
