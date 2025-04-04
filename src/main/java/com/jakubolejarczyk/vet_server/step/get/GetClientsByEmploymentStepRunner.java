//package com.jakubolejarczyk.vet_server.step.get;
//
//import com.jakubolejarczyk.vet_server.model.dependent.Employment;
//import com.jakubolejarczyk.vet_server.service.dependent.ClientService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class GetClientsByEmploymentStepRunner implements StepRunnerModel {
//    private final ClientService clientService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        if (stepStore.hasNotItem("employments")) throw new Error("The employments is required!");
//        val employments = stepStore.getItemAsArray("employments", Employment.class);
//        val clinicsIds = employments.stream()
//                .map(Employment::getClinicId)
//                .toList();
//        val clients = clientService.findAllByClinicIdIn(clinicsIds);
//        stepStore.setItem("clients", clients);
//    }
//}
