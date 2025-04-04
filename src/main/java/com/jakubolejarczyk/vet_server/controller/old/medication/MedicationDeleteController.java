package com.jakubolejarczyk.vet_server.controller.old.medication;

import com.jakubolejarczyk.vet_server.dto.request.logic.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessDeleteMedicationStepRunner;
import com.jakubolejarczyk.vet_server.step.update.UpdateMedicationIsArchivedStepRunner;
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
public class MedicationDeleteController extends StepRunnerController {
    private final GetAccountByTokenStepRunner getAccountByTokenStep;
    private final UpdateMedicationIsArchivedStepRunner updateMedicationIsArchivedStep;
    private final SuccessDeleteMedicationStepRunner successDeleteMedicationStep;

    public MedicationDeleteController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            UpdateMedicationIsArchivedStepRunner updateMedicationIsArchivedStep,
            SuccessDeleteMedicationStepRunner successDeleteMedicationStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.updateMedicationIsArchivedStep = updateMedicationIsArchivedStep;
        this.successDeleteMedicationStep = successDeleteMedicationStep;
    }

    @PostMapping("medication-delete")
    public ResponseEntity<Response<?, ?>> medicationUpdate(@Valid @RequestBody DeleteRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(updateMedicationIsArchivedStep);
        steps.addLast(successDeleteMedicationStep);
        String[] dataKeys = {};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("medicationIds", request.getIds());
        return runController(steps);
    }
}
