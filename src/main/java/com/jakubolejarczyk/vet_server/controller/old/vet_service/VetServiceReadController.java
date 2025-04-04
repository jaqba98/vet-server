//package com.jakubolejarczyk.vet_server.controller.old.vet_service;
//
//import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
//import com.jakubolejarczyk.vet_server.dto.response.Response;
//import com.jakubolejarczyk.vet_server.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
//import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
//import com.jakubolejarczyk.vet_server.step.get.GetEmploymentsByAccountIdAndIsOwnerStepRunner;
//import com.jakubolejarczyk.vet_server.step.get.GetVetServicesByEmploymentStepRunner;
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
//public class VetServiceReadController extends StepRunnerController {
//    private final GetAccountByTokenStepRunner getAccountByTokenStep;
//    private final GetEmploymentsByAccountIdAndIsOwnerStepRunner getEmploymentsByAccountIdAndIsOwnerStep;
//    private final GetVetServicesByEmploymentStepRunner vetServicesByEmploymentStep;
//
//    public VetServiceReadController(
//            ObjectFactory<StepStore> stepStoreObjectFactory,
//            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
//            GetAccountByTokenStepRunner getAccountByTokenStep,
//            GetEmploymentsByAccountIdAndIsOwnerStepRunner getEmploymentsByAccountIdAndIsOwnerStep,
//            GetVetServicesByEmploymentStepRunner vetServicesByEmploymentStep
//    ) {
//        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
//        this.getAccountByTokenStep = getAccountByTokenStep;
//        this.getEmploymentsByAccountIdAndIsOwnerStep = getEmploymentsByAccountIdAndIsOwnerStep;
//        this.vetServicesByEmploymentStep = vetServicesByEmploymentStep;
//    }
//
//    @PostMapping("vet-service-read")
//    public ResponseEntity<Response<?, ?>> vetServiceRead(@Valid @RequestBody TokenRequest request) {
//        val steps = new ArrayList<StepRunnerModel>();
//        steps.addLast(getAccountByTokenStep);
//        steps.addLast(getEmploymentsByAccountIdAndIsOwnerStep);
//        steps.addLast(vetServicesByEmploymentStep);
//        String[] dataKeys = {"vetServices"};
//        String[] metadataKeys = {};
//        initController(dataKeys, metadataKeys);
//        getStepStore().setItem("token", request.getToken());
//        return runController(steps);
//    }
//}
