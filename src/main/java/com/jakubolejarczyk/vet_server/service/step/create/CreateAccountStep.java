//package com.jakubolejarczyk.vet_server.service.step.create;
//
//import com.jakubolejarczyk.vet_server.model.independent.Account;
//import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
//import com.jakubolejarczyk.vet_server.service.input.create.CreateAccountInput;
//import com.jakubolejarczyk.vet_server.service.model.StepModel;
//import com.jakubolejarczyk.vet_server.service.model.StepOutput;
//import com.jakubolejarczyk.vet_server.service.security.PasswordService;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class CreateAccountStep implements StepModel<CreateAccountInput, Account> {
//    private final AccountService accountService;
//    private final PasswordService passwordService;
//
//    @Override
//    public StepOutput<Account> runStep(CreateAccountInput input) {
//        try {
//            val email = input.email();
//            val accountByEmail = accountService.findByEmail(email);
//            if (accountByEmail.isPresent()) {
//                return StepOutput.<Account>builder()
//                        .success(false)
//                        .build();
//            }
//            val password = input.password();
//            val firstName = input.firstName();
//            val lastName = input.lastName();
//            val hashPassword = passwordService.encode(password);
//            val account = Account.builder()
//                    .email(input.email())
//                    .password(hashPassword)
//                    .firstName(firstName)
//                    .lastName(lastName)
//                    .isArchived(false)
//                    .build();
//            val newAccount = accountService.create(account);
//            return StepOutput.<Account>builder()
//                    .success(true)
//                    .output(newAccount)
//                    .build();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
