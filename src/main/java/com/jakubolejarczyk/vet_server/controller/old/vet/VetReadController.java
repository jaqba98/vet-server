//package com.jakubolejarczyk.vet_server.controller.old.vet;
//
//import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
//import com.jakubolejarczyk.vet_server.dto.response.Response;
//import com.jakubolejarczyk.vet_server.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
//import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
//import com.jakubolejarczyk.vet_server.step.get.GetVetByAccountStepRunner;
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
//public class VetReadController extends StepRunnerController {
//    private final GetAccountByTokenStepRunner getAccountByTokenStep;
//    private final GetVetByAccountStepRunner getVetByAccountStep;
//
//    public VetReadController(
//            ObjectFactory<StepStore> stepStoreObjectFactory,
//            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
//            GetAccountByTokenStepRunner getAccountByTokenStep,
//            GetVetByAccountStepRunner getVetByAccountStep
//    ) {
//        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
//        this.getAccountByTokenStep = getAccountByTokenStep;
//        this.getVetByAccountStep = getVetByAccountStep;
//    }
//
//    @PostMapping("vet-read")
//    public ResponseEntity<Response<?, ?>> vetRead(@Valid @RequestBody TokenRequest request) {
//        val steps = new ArrayList<StepRunnerModel>();
//        steps.addLast(getAccountByTokenStep);
//        steps.addLast(getVetByAccountStep);
//        String[] dataKeys = {"vet"};
//        String[] metadataKeys = {};
//        initController(dataKeys, metadataKeys);
//        getStepStore().setItem("token", request.getToken());
//        return runController(steps);
//    }
//}
