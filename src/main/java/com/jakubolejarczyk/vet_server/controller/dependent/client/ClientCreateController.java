package com.jakubolejarczyk.vet_server.controller.dependent.client;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ClientData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ClientMetadata;
import com.jakubolejarczyk.vet_server.dto.request.dependent.ClientRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStep;
import com.jakubolejarczyk.vet_server.step.create.CreateClientStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.client.ClientCreateResponseStep;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
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
public class ClientCreateController extends StepRunnerController<ClientData, ClientMetadata> {
    private final GetAccountByTokenStep<ClientData, ClientMetadata> getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStep<ClientData, ClientMetadata> checkAccountPermissionToClinicStep;
    private final CreateClientStep<ClientData, ClientMetadata> createClientStep;
    private final ClientCreateResponseStep clinicCreateResponseStep;

    public ClientCreateController(
        ObjectFactory<StepStore<ClientData, ClientMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<ClientData, ClientMetadata> getAccountByTokenStep,
        CheckAccountPermissionToClinicStep<ClientData, ClientMetadata> checkAccountPermissionToClinicStep,
        CreateClientStep<ClientData, ClientMetadata> createClientStep,
        ClientCreateResponseStep clinicCreateResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.createClientStep = createClientStep;
        this.clinicCreateResponseStep = clinicCreateResponseStep;
    }

    @PostMapping("client-create")
    public ResponseEntity<Response<ClientData, ClientMetadata>> clientCreate(
        @Valid @RequestBody ClientRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<ClientData, ClientMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(createClientStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        val clientRequest = Client.builder()
            .id(request.getId())
            .email(request.getEmail())
            .phoneNumber(request.getPhoneNumber())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .clinicId(request.getClinicId())
            .build();
        getStepStore().setItem("clientRequest", clientRequest);
        getStepStore().setItem("clinicId", request.getClinicId());
        return runController(steps, clinicCreateResponseStep);
    }
}
