//package com.jakubolejarczyk.vet_server.step.check;
//
//import com.jakubolejarczyk.vet_server.service.independent.AccountService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class CheckAccountNotExistByEmailStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
//    private final AccountService accountService;
//
//    @Override
//    public void runStep(StepStore<TData, TMetadata> stepStore) {
//        if (stepStore.hasNotItem("email")) throw new Error("The email is required!");
//        val email = stepStore.getItem("email", String.class);
//        val account = accountService.findByEmail(email);
//        if (account.isEmpty()) return;
//        stepStore.setSuccess(false);
//        stepStore.addMessage("There is already an account with the given email address!");
//    }
//}
