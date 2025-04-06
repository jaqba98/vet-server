//package com.jakubolejarczyk.vet_server.controller.guard;
//
//import com.jakubolejarczyk.vet_server.dto.data.guard.ValidTokenData;
//import com.jakubolejarczyk.vet_server.dto.metadata.guard.ValidTokenMetadata;
//import com.jakubolejarczyk.vet_server.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.step.check.CheckTokenStep;
//import com.jakubolejarczyk.vet_server.step.response.guard.ValidTokenResponseStep;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1")
//public class ValidTokenController extends StepRunnerController<ValidTokenData, ValidTokenMetadata> {
//    // I am here
//    private final CheckTokenStep<ValidTokenData, ValidTokenMetadata> checkTokenStep;
//    private final ValidTokenResponseStep validTokenResponseStep;
//
//    public ValidTokenController(
//        ObjectFactory<StepStore<ValidTokenData, ValidTokenMetadata>> stepStoreObjectFactory,
//        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
//        CheckTokenStep<ValidTokenData, ValidTokenMetadata> checkTokenStep,
//        ValidTokenResponseStep validTokenResponseStep
//    ) {
//        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
//        this.checkTokenStep = checkTokenStep;
//        this.validTokenResponseStep = validTokenResponseStep;
//    }
//
//    @PostMapping("valid-token")
//    public ResponseEntity<Response<ValidTokenData, ValidTokenMetadata>> validToken(@Valid @RequestBody TokenRequest request) {
//        val steps = new ArrayList<StepRunnerModel<ValidTokenData, ValidTokenMetadata>>();
//        steps.addLast(checkTokenStep);
//        initController();
//        getStepStore().setItem("token", request.getToken());
//        return runController(steps, validTokenResponseStep);
//    }
//}
