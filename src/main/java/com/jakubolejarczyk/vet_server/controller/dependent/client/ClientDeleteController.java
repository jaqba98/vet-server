package com.jakubolejarczyk.vet_server.controller.dependent.client;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ClientData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ClientMetadata;
import com.jakubolejarczyk.vet_server.dto.request.logic.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.delete.DeleteClientsStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.client.ClientDeleteResponseStep;
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
public class ClientDeleteController extends StepRunnerController<ClientData, ClientMetadata> {
    private final GetAccountByTokenStep<ClientData, ClientMetadata> getAccountByTokenStep;
    private final DeleteClientsStep<ClientData, ClientMetadata> deleteClientsStep;
    private final ClientDeleteResponseStep clientDeleteResponseStep;

    public ClientDeleteController(
        ObjectFactory<StepStore<ClientData, ClientMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<ClientData, ClientMetadata> getAccountByTokenStep,
        DeleteClientsStep<ClientData, ClientMetadata> deleteClientsStep,
        ClientDeleteResponseStep clientDeleteResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.deleteClientsStep = deleteClientsStep;
        this.clientDeleteResponseStep = clientDeleteResponseStep;
    }

    @PostMapping("client-delete")
    public ResponseEntity<Response<ClientData, ClientMetadata>> clientDelete(
        @Valid @RequestBody DeleteRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<ClientData, ClientMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(deleteClientsStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("clientIds", request.getIds());
        return runController(steps, clientDeleteResponseStep);
    }
}
