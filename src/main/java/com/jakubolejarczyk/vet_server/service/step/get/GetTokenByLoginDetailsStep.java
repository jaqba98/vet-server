//package com.jakubolejarczyk.vet_server.service.step.get;
//
//import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
//import com.jakubolejarczyk.vet_server.service.model.StepModel;
//import com.jakubolejarczyk.vet_server.service.security.PasswordService;
//import com.jakubolejarczyk.vet_server.service.security.TokenService;
//import com.jakubolejarczyk.vet_server.service.store.StepStore;
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
//    public StepOutput runStep(StepStore stepStore) {
//        try {
//            val email = (String) stepStore.get("email");
//            val account = accountService.findByEmail(email);
//            if (account.isPresent()) {
//                val password = (String) stepStore.get("password");
//                val encodedPassword = account.get().getPassword();
//                if (passwordService.match(password, encodedPassword)) {
//                    val token = tokenService.generate(email);
//                    stepStore.set(token, token);
//                    return StepOutput.builder()
//                            .success(true)
//                            .message("You have logged in successfully")
//                            .build();
//                }
//            }
//            return StepOutput.builder()
//                    .success(false)
//                    .message("Invalid email or password")
//                    .build();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
