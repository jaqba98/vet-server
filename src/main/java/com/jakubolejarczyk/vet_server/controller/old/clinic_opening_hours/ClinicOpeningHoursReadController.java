//package com.jakubolejarczyk.vet_server.controller.old.clinic_opening_hours;
//
//import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
//import com.jakubolejarczyk.vet_server.dto.response.Response;
//import com.jakubolejarczyk.vet_server.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
//import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
//import com.jakubolejarczyk.vet_server.step.get.GetClinicsByEmploymentStepRunner;
//import com.jakubolejarczyk.vet_server.step.get.GetEmploymentsByAccountIdAndIsOwnerStepRunner;
//import com.jakubolejarczyk.vet_server.step.get.GetOpeningHoursByClinicsStepRunner;
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
//public class ClinicOpeningHoursReadController extends StepRunnerController {
//    private final GetAccountByTokenStepRunner getAccountByTokenStep;
//    private final GetEmploymentsByAccountIdAndIsOwnerStepRunner getEmploymentsByAccountIdAndIsOwnerStep;
//    private final GetClinicsByEmploymentStepRunner getClinicsByEmploymentStep;
//    private final GetOpeningHoursByClinicsStepRunner getOpeningHoursByClinicsStep;
//
//    public ClinicOpeningHoursReadController(
//            ObjectFactory<StepStore> stepStoreObjectFactory,
//            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
//            GetAccountByTokenStepRunner getAccountByTokenStep,
//            GetEmploymentsByAccountIdAndIsOwnerStepRunner getEmploymentsByAccountIdAndIsOwnerStep,
//            GetClinicsByEmploymentStepRunner getClinicsByEmploymentStep,
//            GetOpeningHoursByClinicsStepRunner getOpeningHoursByClinicsStep
//    ) {
//        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
//        this.getAccountByTokenStep = getAccountByTokenStep;
//        this.getEmploymentsByAccountIdAndIsOwnerStep = getEmploymentsByAccountIdAndIsOwnerStep;
//        this.getClinicsByEmploymentStep = getClinicsByEmploymentStep;
//        this.getOpeningHoursByClinicsStep = getOpeningHoursByClinicsStep;
//    }
//
//    @PostMapping("clinic-opening-hours-read")
//    public ResponseEntity<Response<?, ?>> clinicOpeningHoursRead(@Valid @RequestBody TokenRequest request) {
//        val steps = new ArrayList<StepRunnerModel>();
//        steps.addLast(getAccountByTokenStep);
//        steps.addLast(getEmploymentsByAccountIdAndIsOwnerStep);
//        steps.addLast(getClinicsByEmploymentStep);
//        steps.addLast(getOpeningHoursByClinicsStep);
//        String[] dataKeys = {"openingHours"};
//        String[] metadataKeys = {};
//        initController(dataKeys, metadataKeys);
//        getStepStore().setItem("token", request.getToken());
//        return runController(steps);
//    }
//}
