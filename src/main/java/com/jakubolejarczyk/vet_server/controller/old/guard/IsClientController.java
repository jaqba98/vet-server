//package com.jakubolejarczyk.vet_server.controller.old.guard;
//
//import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
//import com.jakubolejarczyk.vet_server.dto.response.Response;
//import com.jakubolejarczyk.vet_server.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
//import com.jakubolejarczyk.vet_server.step.check.CheckAccountIsClientStepRunner;
//import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import jakarta.validation.Valid;
//import lombok.val;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//
//@RestController
//@RequestMapping("/api/v1")
//public class IsClientController extends StepRunnerController {
//    private final GetAccountByTokenStepRunner getAccountByTokenStep;
//    private final CheckAccountIsClientStepRunner checkAccountIsClientStep;
//
//    public IsClientController(
//            ObjectFactory<StepStore> stepStoreObjectFactory,
//            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
//            GetAccountByTokenStepRunner getAccountByTokenStep,
//            CheckAccountIsClientStepRunner checkAccountIsClientStep
//    ) {
//        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
//        this.getAccountByTokenStep = getAccountByTokenStep;
//        this.checkAccountIsClientStep = checkAccountIsClientStep;
//    }
//
//    @PostMapping("is-client")
//    public ResponseEntity<Response<?, ?>> isClient(@Valid @RequestBody TokenRequest request) {
//        val steps = new ArrayList<StepRunnerModel>();
//        steps.addLast(getAccountByTokenStep);
//        steps.addLast(checkAccountIsClientStep);
//        String[] dataKeys = {};
//        String[] metadataKeys = {};
//        initController(dataKeys, metadataKeys);
//        getStepStore().setItem("token", request.getToken());
//        return runController(steps);
//    }
//}
