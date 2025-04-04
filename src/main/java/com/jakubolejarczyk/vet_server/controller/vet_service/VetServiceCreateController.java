package com.jakubolejarczyk.vet_server.controller.vet_service;

import com.jakubolejarczyk.vet_server.dto.request.dependent.ServiceClinicRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStepRunner;
import com.jakubolejarczyk.vet_server.step.create.CreateVetServiceStepRunner;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessCreateVetServiceStepRunner;
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
public class VetServiceCreateController extends StepRunnerController {
    private final GetAccountByTokenStepRunner getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStepRunner checkAccountPermissionToClinicStep;
    private final CreateVetServiceStepRunner createVetServiceStep;
    private final SuccessCreateVetServiceStepRunner successCreateVetServiceStep;

    public VetServiceCreateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            CheckAccountPermissionToClinicStepRunner checkAccountPermissionToClinicStep,
            CreateVetServiceStepRunner createVetServiceStep,
            SuccessCreateVetServiceStepRunner successCreateVetServiceStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.createVetServiceStep = createVetServiceStep;
        this.successCreateVetServiceStep = successCreateVetServiceStep;
    }

    @PostMapping("vet-service-create")
    public ResponseEntity<Response<?, ?>> vetServiceCreate(@Valid @RequestBody ServiceClinicRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(createVetServiceStep);
        steps.addLast(successCreateVetServiceStep);
        String[] dataKeys = {};
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
