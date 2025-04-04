package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.logic.ChooseRoleRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.set.SetAccountRoleStepRunner;
import com.jakubolejarczyk.vet_server.store.StepStore;
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
public class ChooseRoleController extends StepRunnerController {
    private final GetAccountByTokenStepRunner getAccountByTokenStep;
    private final SetAccountRoleStepRunner setAccountRoleStep;

    public ChooseRoleController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            SetAccountRoleStepRunner setAccountRoleStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.setAccountRoleStep = setAccountRoleStep;
    }

    @PostMapping("choose-role")
    public ResponseEntity<Response<?, ?>> chooseRole(@RequestBody ChooseRoleRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(setAccountRoleStep);
        String[] dataKeys = {};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("roleToSet", request.getRole());
        return runController(steps);
    }
}
