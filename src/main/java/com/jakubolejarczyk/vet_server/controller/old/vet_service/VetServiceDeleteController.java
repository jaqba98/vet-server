package com.jakubolejarczyk.vet_server.controller.old.vet_service;

import com.jakubolejarczyk.vet_server.dto.request.logic.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessVetServicesMedicationStepRunner;
import com.jakubolejarczyk.vet_server.step.update.UpdateVetServicesIsArchivedStepRunner;
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
public class VetServiceDeleteController extends StepRunnerController {
    private final GetAccountByTokenStepRunner getAccountByTokenStep;
    private final UpdateVetServicesIsArchivedStepRunner updateVetServicesIsArchivedStep;
    private final SuccessVetServicesMedicationStepRunner successVetServicesMedicationStep;

    public VetServiceDeleteController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            UpdateVetServicesIsArchivedStepRunner updateVetServicesIsArchivedStep,
            SuccessVetServicesMedicationStepRunner successVetServicesMedicationStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.updateVetServicesIsArchivedStep = updateVetServicesIsArchivedStep;
        this.successVetServicesMedicationStep = successVetServicesMedicationStep;
    }

    @PostMapping("vet-service-delete")
    public ResponseEntity<Response<?, ?>> vetServiceDelete(@Valid @RequestBody DeleteRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(updateVetServicesIsArchivedStep);
        steps.addLast(successVetServicesMedicationStep);
        String[] dataKeys = {"vetService"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("vetServicesIds", request.getIds());
        return runController(steps);
    }
}
