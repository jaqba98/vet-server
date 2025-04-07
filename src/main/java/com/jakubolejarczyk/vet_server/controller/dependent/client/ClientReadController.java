package com.jakubolejarczyk.vet_server.controller.dependent.client;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ClientData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ClientMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.client.GetClientsByClinicsStep;
import com.jakubolejarczyk.vet_server.step.get.clinic.GetClinicsByEmploymentsStep;
import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentsByAccountStep;
import com.jakubolejarczyk.vet_server.step.metadata.ClientMetadataStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.client.ClientReadResponseStep;
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
public class ClientReadController extends StepRunnerController<ClientData, ClientMetadata> {
    private final GetAccountByTokenStep<ClientData, ClientMetadata> getAccountByTokenStep;
    private final GetEmploymentsByAccountStep<ClientData, ClientMetadata> getEmploymentsByAccountStep;
    private final GetClinicsByEmploymentsStep<ClientData, ClientMetadata> getClinicsByEmploymentsStep;
    private final GetClientsByClinicsStep<ClientData, ClientMetadata> getClientsByClinicsStep;
    private final ClientMetadataStep clientMetadataStep;
    private final ClientReadResponseStep clientReadResponseStep;

    public ClientReadController(
        ObjectFactory<StepStore<ClientData, ClientMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<ClientData, ClientMetadata> getAccountByTokenStep,
        GetEmploymentsByAccountStep<ClientData, ClientMetadata> getEmploymentsByAccountStep,
        GetClinicsByEmploymentsStep<ClientData, ClientMetadata> getClinicsByEmploymentsStep,
        GetClientsByClinicsStep<ClientData, ClientMetadata> getClientsByClinicsStep,
        ClientMetadataStep clientMetadataStep,
        ClientReadResponseStep clientReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountStep = getEmploymentsByAccountStep;
        this.getClinicsByEmploymentsStep = getClinicsByEmploymentsStep;
        this.getClientsByClinicsStep = getClientsByClinicsStep;
        this.clientMetadataStep = clientMetadataStep;
        this.clientReadResponseStep = clientReadResponseStep;
    }

    @PostMapping("client-read")
    public ResponseEntity<Response<ClientData, ClientMetadata>> clientRead(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<ClientData, ClientMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountStep);
        steps.addLast(getClinicsByEmploymentsStep);
        steps.addLast(getClientsByClinicsStep);
        steps.addLast(clientMetadataStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, clientReadResponseStep);
    }
}
