package com.jakubolejarczyk.vet_server.controller.vet_service;

import com.jakubolejarczyk.vet_server.dto.request.common.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessVetServicesMedicationStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateVetServicesIsArchivedStep;
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
public class VetServiceDeleteController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final UpdateVetServicesIsArchivedStep updateVetServicesIsArchivedStep;
    private final SuccessVetServicesMedicationStep successVetServicesMedicationStep;

    public VetServiceDeleteController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            UpdateVetServicesIsArchivedStep updateVetServicesIsArchivedStep,
            SuccessVetServicesMedicationStep successVetServicesMedicationStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.updateVetServicesIsArchivedStep = updateVetServicesIsArchivedStep;
        this.successVetServicesMedicationStep = successVetServicesMedicationStep;
    }

    @PostMapping("vet-service-delete")
    public ResponseEntity<Response<?, ?>> vetServiceDelete(@Valid @RequestBody DeleteRequest request) {
        val steps = new ArrayList<StepModel>();
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
