//package com.jakubolejarczyk.vet_server.controller.old.common;
//
//import com.jakubolejarczyk.vet_server.dto.request.logic.RegistrationRequest;
//import com.jakubolejarczyk.vet_server.dto.response.Response;
//import com.jakubolejarczyk.vet_server.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
//import com.jakubolejarczyk.vet_server.step.check.CheckAccountNotExistStepRunner;
//import com.jakubolejarczyk.vet_server.step.create.CreateAccountStepRunner;
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
//public class RegistrationController extends StepRunnerController {
//    private final CheckAccountNotExistStepRunner checkAccountNotExistStep;
//    private final CreateAccountStepRunner createAccountStep;
//
//    public RegistrationController(
//            ObjectFactory<StepStore> stepStoreObjectFactory,
//            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
//            CheckAccountNotExistStepRunner checkAccountNotExistStep,
//            CreateAccountStepRunner createAccountStep
//    ) {
//        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
//        this.checkAccountNotExistStep = checkAccountNotExistStep;
//        this.createAccountStep = createAccountStep;
//    }
//
//    @PostMapping("registration")
//    public ResponseEntity<Response<?, ?>> registration(@Valid @RequestBody RegistrationRequest request) {
//        val steps = new ArrayList<StepRunnerModel>();
//        steps.addLast(checkAccountNotExistStep);
//        steps.addLast(createAccountStep);
//        String[] dataKeys = {};
//        String[] metadataKeys = {};
//        initController(dataKeys, metadataKeys);
//        getStepStore().setItem("email", request.getEmail());
//        getStepStore().setItem("password", request.getPassword());
//        getStepStore().setItem("firstName", request.getFirstName());
//        getStepStore().setItem("lastName", request.getLastName());
//        return runController(steps);
//    }
//}
