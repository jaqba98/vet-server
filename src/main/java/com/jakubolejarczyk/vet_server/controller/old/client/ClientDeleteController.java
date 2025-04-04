package com.jakubolejarczyk.vet_server.controller.old.client;

import com.jakubolejarczyk.vet_server.dto.request.logic.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessDeleteClientStepRunner;
import com.jakubolejarczyk.vet_server.step.update.UpdateClientIsArchivedStepRunner;
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
public class ClientDeleteController extends StepRunnerController {
    private final GetAccountByTokenStepRunner getAccountByTokenStep;
    private final UpdateClientIsArchivedStepRunner updateClientIsArchivedStep;
    private final SuccessDeleteClientStepRunner successDeleteClientStep;

    public ClientDeleteController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            UpdateClientIsArchivedStepRunner updateClientIsArchivedStep,
            SuccessDeleteClientStepRunner successDeleteClientStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.updateClientIsArchivedStep = updateClientIsArchivedStep;
        this.successDeleteClientStep = successDeleteClientStep;
    }

    @PostMapping("client-delete")
    public ResponseEntity<Response<?, ?>> clientDelete(@Valid @RequestBody DeleteRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(updateClientIsArchivedStep);
        steps.addLast(successDeleteClientStep);
        String[] dataKeys = {};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("clientIds", request.getIds());
        return runController(steps);
    }
}
