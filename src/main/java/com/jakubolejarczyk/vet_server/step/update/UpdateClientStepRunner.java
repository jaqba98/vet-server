//package com.jakubolejarczyk.vet_server.step.update;
//
//import com.jakubolejarczyk.vet_server.model.dependent.Client;
//import com.jakubolejarczyk.vet_server.service.dependent.ClientService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class UpdateClientStepRunner implements StepRunnerModel {
//    private final ClientService clientService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        if (stepStore.hasNotItem("requestClient")) throw new Error("The requestClient is required!");
//        val requestClient = stepStore.getItem("requestClient", Client.class);
//        val clientId = requestClient.getId();
//        val currentClient = clientService.findById(clientId);
//        if (currentClient.isPresent()) {
//            val newClient = Client.builder()
//                    .id(currentClient.get().getId())
//                    .email(requestClient.getEmail())
//                    .phoneNumber(requestClient.getPhoneNumber())
//                    .firstName(requestClient.getFirstName())
//                    .lastName(requestClient.getLastName())
//                    .clinicId(requestClient.getClinicId())
//                    .build();
//            val client = clientService.save(newClient);
//            stepStore.setItem("client", client);
//            return;
//        }
//        stepStore.setSuccess(false);
//        stepStore.addMessage("Failed to update a client.");
//    }
//}
