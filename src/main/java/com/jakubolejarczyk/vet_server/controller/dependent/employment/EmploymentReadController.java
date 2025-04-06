//package com.jakubolejarczyk.vet_server.controller.dependent.employment;
//
//import com.jakubolejarczyk.vet_server.dto.data.dependent.EmploymentData;
//import com.jakubolejarczyk.vet_server.dto.metadata.dependent.EmploymentMetadata;
//import com.jakubolejarczyk.vet_server.dto.request.dependent.EmploymentRequest;
//import com.jakubolejarczyk.vet_server.dto.response.Response;
//import com.jakubolejarczyk.vet_server.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
//import com.jakubolejarczyk.vet_server.step.get.GetClinicsByEmploymentStep;
//import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentsByAccountStep;
//import com.jakubolejarczyk.vet_server.step.response.dependent.employment.EmploymentReadResponseStep;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
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
//public class EmploymentReadController extends StepRunnerController<EmploymentData, EmploymentMetadata> {
//    private final GetAccountByTokenStep<EmploymentData, EmploymentMetadata> getAccountByTokenStep;
//    private final GetEmploymentsByAccountStep<EmploymentData, EmploymentMetadata> getEmploymentsByAccountIdStep;
//    private final GetClinicsByEmploymentStep<EmploymentData, EmploymentMetadata> getClinicsByEmploymentStep;
//    private final EmploymentReadResponseStep employmentReadResponseStep;
//
//    public EmploymentReadController(
//        ObjectFactory<StepStore<EmploymentData, EmploymentMetadata>> stepStoreObjectFactory,
//        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
//        GetAccountByTokenStep<EmploymentData, EmploymentMetadata> getAccountByTokenStep,
//        GetEmploymentsByAccountStep<EmploymentData, EmploymentMetadata> getEmploymentsByAccountIdStep,
//        GetClinicsByEmploymentStep<EmploymentData, EmploymentMetadata> getClinicsByEmploymentStep,
//        EmploymentReadResponseStep employmentReadResponseStep
//    ) {
//        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
//        this.getAccountByTokenStep = getAccountByTokenStep;
//        this.getEmploymentsByAccountIdStep = getEmploymentsByAccountIdStep;
//        this.getClinicsByEmploymentStep = getClinicsByEmploymentStep;
//        this.employmentReadResponseStep = employmentReadResponseStep;
//    }
//
//    @PostMapping("employment-read")
//    public ResponseEntity<Response<EmploymentData, EmploymentMetadata>> employmentRead(
//        @Valid @RequestBody EmploymentRequest request
//    ) {
//        val steps = new ArrayList<StepRunnerModel<EmploymentData, EmploymentMetadata>>();
//        steps.addLast(getAccountByTokenStep);
//        steps.addLast(getEmploymentsByAccountIdStep);
//        steps.addLast(getClinicsByEmploymentStep);
//        initController();
//        getStepStore().setItem("token", request.getToken());
//        return runController(steps, employmentReadResponseStep);
//    }
//}
