//package com.jakubolejarczyk.vet_server.service.step.get;
//
//import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
//import com.jakubolejarczyk.vet_server.service.security.TokenService;
//import com.jakubolejarczyk.vet_server.step.model.StepModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class GetAccountByTokenStep implements StepModel {
//    private final AccountService accountService;
//    private final TokenService tokenService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        val token = (String) stepStore.getItem("token");
//        val email = tokenService.decode(token);
//        val account = accountService.findByEmail(email);
//        if (account.isPresent()) {
//            stepStore.setItem("accountId", account.get().getId());
//            stepStore.setItem("email", account.get().getEmail());
//            stepStore.setItem("firstName", account.get().getFirstName());
//            stepStore.setItem("lastName", account.get().getLastName());
//            stepStore.setItem("role", account.get().getRole());
//            stepStore.setItem("pictureUrl", account.get().getPictureUrl());
//            stepStore.addMessage("Account was loaded");
//            return;
//        }
//        stepStore.addMessage("Account was not loaded");
//        stepStore.setSuccess(false);
//    }
//}
