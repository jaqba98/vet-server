package com.jakubolejarczyk.vet_server.controller.client;

import com.jakubolejarczyk.vet_server.dto.request.ClientRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStepRunner;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessUpdateClientStepRunner;
import com.jakubolejarczyk.vet_server.step.update.UpdateClientStepRunner;
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
public class ClientUpdateController extends StepRunnerController {
    private final GetAccountByTokenStepRunner getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStepRunner checkAccountPermissionToClinicStep;
    private final UpdateClientStepRunner updateClientStep;
    private final SuccessUpdateClientStepRunner successUpdateClientStep;

    public ClientUpdateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            CheckAccountPermissionToClinicStepRunner checkAccountPermissionToClinicStep,
            UpdateClientStepRunner updateClientStep,
            SuccessUpdateClientStepRunner successUpdateClientStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.updateClientStep = updateClientStep;
        this.successUpdateClientStep = successUpdateClientStep;
    }

    @PostMapping("client-update")
    public ResponseEntity<Response<?, ?>> clientUpdate(@Valid @RequestBody ClientRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(updateClientStep);
        steps.addLast(successUpdateClientStep);
        String[] dataKeys = {"client"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val requestClient = Client.builder()
                .id(request.getId())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .clinicId(request.getClinicId())
                .build();
        getStepStore().setItem("requestClient", requestClient);
        getStepStore().setItem("clinicId", request.getClinicId());
        return runController(steps);
    }
}
