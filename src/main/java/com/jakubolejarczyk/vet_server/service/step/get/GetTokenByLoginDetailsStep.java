//package com.jakubolejarczyk.vet_server.service.step.get;
//
//import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
//import com.jakubolejarczyk.vet_server.service.security.PasswordService;
//import com.jakubolejarczyk.vet_server.service.security.TokenService;
//import com.jakubolejarczyk.vet_server.step.model.StepModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class GetTokenByLoginDetailsStep implements StepModel {
//    private final AccountService accountService;
//    private final PasswordService passwordService;
//    private final TokenService tokenService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        val email = (String) stepStore.getItem("email");
//        val account = accountService.findByEmail(email);
//        if (account.isPresent()) {
//            val password = (String) stepStore.getItem("password");
//            val encodedPassword = account.get().getPassword();
//            if (passwordService.match(password, encodedPassword)) {
//                val token = tokenService.generate(email);
//                stepStore.setItem("token", token);
//                stepStore.addMessage("You have logged in successfully");
//                return;
//            }
//        }
//        stepStore.setSuccess(false);
//        stepStore.addMessage("Invalid email or password");
//    }
//}
