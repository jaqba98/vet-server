//package com.jakubolejarczyk.vet_server.step.get;
//
//import com.jakubolejarczyk.vet_server.model.independent.Account;
//import com.jakubolejarczyk.vet_server.service.dependent.VetService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class GetVetByAccountStepRunner implements StepRunnerModel {
//    private final VetService vetService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
//        val account = stepStore.getItem("account", Account.class);
//        val accountId = account.getId();
//        val vet = vetService.findByAccountId(accountId);
//        if (vet.isPresent()) {
//            stepStore.setItem("vet", vet.get());
//            return;
//        }
//        stepStore.setSuccess(false);
//        stepStore.addMessage("Failed to read vet details. Vet does not exist!");
//    }
//}
