package com.jakubolejarczyk.vet_server.controller.medication;

import com.jakubolejarczyk.vet_server.dto.request.common.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessDeleteMedicationStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateMedicationIsArchivedStep;
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
public class MedicationDeleteController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final UpdateMedicationIsArchivedStep updateMedicationIsArchivedStep;
    private final SuccessDeleteMedicationStep successDeleteMedicationStep;

    public MedicationDeleteController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            UpdateMedicationIsArchivedStep updateMedicationIsArchivedStep,
            SuccessDeleteMedicationStep successDeleteMedicationStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.updateMedicationIsArchivedStep = updateMedicationIsArchivedStep;
        this.successDeleteMedicationStep = successDeleteMedicationStep;
    }

    @PostMapping("medication-delete")
    public ResponseEntity<Response<?, ?>> medicationUpdate(@Valid @RequestBody DeleteRequest request) {
        val steps = new ArrayList<StepModel>();
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
