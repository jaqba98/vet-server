package com.jakubolejarczyk.vet_server.controller.medication;

import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step.get.GetEmploymentsByAccountIdAndIsOwnerStepRunner;
import com.jakubolejarczyk.vet_server.step.get.GetMedicationsByEmploymentStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class MedicationReadController extends StepRunnerController {
    private final GetAccountByTokenStepRunner getAccountByTokenStep;
    private final GetEmploymentsByAccountIdAndIsOwnerStepRunner getEmploymentsByAccountIdAndIsOwnerStep;
    private final GetMedicationsByEmploymentStepRunner getMedicationsByEmploymentStep;

    public MedicationReadController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            GetEmploymentsByAccountIdAndIsOwnerStepRunner getEmploymentsByAccountIdAndIsOwnerStep,
            GetMedicationsByEmploymentStepRunner getMedicationsByEmploymentStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountIdAndIsOwnerStep = getEmploymentsByAccountIdAndIsOwnerStep;
        this.getMedicationsByEmploymentStep = getMedicationsByEmploymentStep;
    }

    @PostMapping("medication-read")
    public ResponseEntity<Response<?, ?>> medicationRead(@Valid @RequestBody TokenRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountIdAndIsOwnerStep);
        steps.addLast(getMedicationsByEmploymentStep);
        String[] dataKeys = {"medications"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        return runController(steps);
    }
}
