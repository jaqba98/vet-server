//package com.jakubolejarczyk.vet_server.step.check;
//
//import com.jakubolejarczyk.vet_server.service.dependent.ClientService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class CheckClientExistStepRunner implements StepRunnerModel {
//    private final ClientService clientService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        if (stepStore.hasNotItem("clientId")) throw new Error("The clientId is required!");
//        val clientId = stepStore.getItem("clientId", Long.class);
//        val client = clientService.findById(clientId);
//        if (client.isPresent()) {
//            return;
//        }
//        stepStore.setSuccess(false);
//        stepStore.addMessage("The client does not exists.");
//    }
//}
