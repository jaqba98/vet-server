package com.jakubolejarczyk.vet_server.controller.client;

import com.jakubolejarczyk.vet_server.dto.request.common.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessDeleteClientStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateClientIsArchivedStep;
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
public class ClientDeleteController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final UpdateClientIsArchivedStep updateClientIsArchivedStep;
    private final SuccessDeleteClientStep successDeleteClientStep;

    public ClientDeleteController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            UpdateClientIsArchivedStep updateClientIsArchivedStep,
            SuccessDeleteClientStep successDeleteClientStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.updateClientIsArchivedStep = updateClientIsArchivedStep;
        this.successDeleteClientStep = successDeleteClientStep;
    }

    @PostMapping("client-delete")
    public ResponseEntity<Response<?, ?>> clientDelete(@Valid @RequestBody DeleteRequest request) {
        val steps = new ArrayList<StepModel>();
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
