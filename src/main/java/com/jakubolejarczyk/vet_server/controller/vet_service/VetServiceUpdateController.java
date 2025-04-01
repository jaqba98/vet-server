package com.jakubolejarczyk.vet_server.controller.vet_service;

import com.jakubolejarczyk.vet_server.controller.medication.MedicationUpdateController;
import com.jakubolejarczyk.vet_server.dto.request.vet_service.VetServiceRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.VetService;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStep;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessUpdateVetServiceStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateVetServiceStep;
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
public class VetServiceUpdateController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStep checkAccountPermissionToClinicStep;
    private final UpdateVetServiceStep updateVetServiceStep;
    private final SuccessUpdateVetServiceStep successUpdateVetServiceStep;

    public VetServiceUpdateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            CheckAccountPermissionToClinicStep checkAccountPermissionToClinicStep,
            UpdateVetServiceStep updateVetServiceStep,
            SuccessUpdateVetServiceStep successUpdateVetServiceStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.updateVetServiceStep = updateVetServiceStep;
        this.successUpdateVetServiceStep = successUpdateVetServiceStep;
    }

    @PostMapping("vet-service-update")
    public ResponseEntity<Response<?, ?>> vetServiceUpdate(@Valid @RequestBody VetServiceRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(updateVetServiceStep);
        steps.addLast(successUpdateVetServiceStep);
        String[] dataKeys = {"vetService"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val requestVetService = VetService.builder()
                .id(request.getId())
                .isArchived(request.getIsArchived())
                .name(request.getName())
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
