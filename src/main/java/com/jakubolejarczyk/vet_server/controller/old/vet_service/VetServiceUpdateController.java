package com.jakubolejarczyk.vet_server.controller.old.vet_service;

import com.jakubolejarczyk.vet_server.dto.request.dependent.ServiceClinicRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStepRunner;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessUpdateVetServiceStepRunner;
import com.jakubolejarczyk.vet_server.step.update.UpdateVetServiceStepRunner;
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
public class VetServiceUpdateController extends StepRunnerController {
    private final GetAccountByTokenStepRunner getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStepRunner checkAccountPermissionToClinicStep;
    private final UpdateVetServiceStepRunner updateVetServiceStep;
    private final SuccessUpdateVetServiceStepRunner successUpdateVetServiceStep;

    public VetServiceUpdateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            CheckAccountPermissionToClinicStepRunner checkAccountPermissionToClinicStep,
            UpdateVetServiceStepRunner updateVetServiceStep,
            SuccessUpdateVetServiceStepRunner successUpdateVetServiceStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.updateVetServiceStep = updateVetServiceStep;
        this.successUpdateVetServiceStep = successUpdateVetServiceStep;
    }

    @PostMapping("vet-service-update")
    public ResponseEntity<Response<?, ?>> vetServiceUpdate(@Valid @RequestBody ServiceClinicRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(updateVetServiceStep);
        steps.addLast(successUpdateVetServiceStep);
        String[] dataKeys = {"vetService"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val requestVetService = ServiceClinic.builder()
                .id(request.getId())
                .fullName(request.getFullName())
                .description(request.getDescription())
                .category(request.getCategory())
                .durationMinutes(request.getDurationMinutes())
                .price(request.getPrice())
                .isAvailable(request.getIsAvailable())
                .clinicId(request.getClinicId())
                .build();
        getStepStore().setItem("requestVetService", requestVetService);
        getStepStore().setItem("clinicId", request.getClinicId());
        return runController(steps);
    }
}
