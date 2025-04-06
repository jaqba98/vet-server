//package com.jakubolejarczyk.vet_server.step.check;
//
//import com.jakubolejarczyk.vet_server.security.TokenService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class CheckTokenStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
//    private final TokenService tokenService;
//
//    @Override
//    public void runStep(StepStore<TData, TMetadata> stepStore) {
//        if (stepStore.hasNotItem("token")) throw new Error("The token is required!");
//        val token = stepStore.getItem("token", String.class);
//        val isValidToken = tokenService.verify(token);
//        if (isValidToken) return;
//        stepStore.setSuccess(false);
//    }
//}
