package com.jakubolejarczyk.vet_server.controller.logic;

import com.jakubolejarczyk.vet_server.dto.data.logic.ChooseRoleData;
import com.jakubolejarczyk.vet_server.dto.metadata.logic.ChooseRoleMetadata;
import com.jakubolejarczyk.vet_server.dto.request.logic.ChooseRoleRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.logic.ChooseRoleResponseStep;
import com.jakubolejarczyk.vet_server.step.set.SetAccountRoleStep;
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
public class ChooseRoleController extends StepRunnerController<ChooseRoleData, ChooseRoleMetadata> {
    private final GetAccountByTokenStep<ChooseRoleData, ChooseRoleMetadata> getAccountByTokenStep;
    private final SetAccountRoleStep<ChooseRoleData, ChooseRoleMetadata> setAccountRoleStep;
    private final ChooseRoleResponseStep chooseRoleResponseStep;

    public ChooseRoleController(
        ObjectFactory<StepStore<ChooseRoleData, ChooseRoleMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<ChooseRoleData, ChooseRoleMetadata> getAccountByTokenStep,
        SetAccountRoleStep<ChooseRoleData, ChooseRoleMetadata> setAccountRoleStep,
        ChooseRoleResponseStep chooseRoleResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.setAccountRoleStep = setAccountRoleStep;
        this.chooseRoleResponseStep = chooseRoleResponseStep;
    }

    @PostMapping("choose-role")
    public ResponseEntity<Response<ChooseRoleData, ChooseRoleMetadata>> chooseRole(
        @Valid @RequestBody ChooseRoleRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<ChooseRoleData, ChooseRoleMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(setAccountRoleStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("role", request.getRole());
        return runController(steps, chooseRoleResponseStep);
    }
}
