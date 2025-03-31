package com.jakubolejarczyk.vet_server.controller.medication;

import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.GetEmploymentsByAccountIdAndIsOwnerStep;
import com.jakubolejarczyk.vet_server.step.get.GetMedicationsByEmploymentStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
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
public class MedicationReadController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final GetEmploymentsByAccountIdAndIsOwnerStep getEmploymentsByAccountIdAndIsOwnerStep;
    private final GetMedicationsByEmploymentStep getMedicationsByEmploymentStep;

    public MedicationReadController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            GetEmploymentsByAccountIdAndIsOwnerStep getEmploymentsByAccountIdAndIsOwnerStep,
            GetMedicationsByEmploymentStep getMedicationsByEmploymentStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountIdAndIsOwnerStep = getEmploymentsByAccountIdAndIsOwnerStep;
        this.getMedicationsByEmploymentStep = getMedicationsByEmploymentStep;
    }

    @PostMapping("medication-read")
    public ResponseEntity<Response<?, ?>> medicationRead(@Valid @RequestBody TokenRequest request) {
        val steps = new ArrayList<StepModel>();
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
