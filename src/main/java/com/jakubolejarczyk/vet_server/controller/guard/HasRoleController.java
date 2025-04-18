package com.jakubolejarczyk.vet_server.controller.guard;

import com.jakubolejarczyk.vet_server.dto.data.guard.HasRoleData;
import com.jakubolejarczyk.vet_server.dto.metadata.guard.HasRoleMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountHasRoleStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.guard.HasRoleResponseStep;
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
public class HasRoleController extends StepRunnerController<HasRoleData, HasRoleMetadata> {
    private final GetAccountByTokenStep<HasRoleData, HasRoleMetadata> getAccountByTokenStep;
    private final CheckAccountHasRoleStep<HasRoleData, HasRoleMetadata> checkAccountHasRoleStep;
    private final HasRoleResponseStep hasRoleResponseStep;

    public HasRoleController(
        ObjectFactory<StepStore<HasRoleData, HasRoleMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<HasRoleData, HasRoleMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<HasRoleData, HasRoleMetadata> getAccountByTokenStep,
        CheckAccountHasRoleStep<HasRoleData, HasRoleMetadata> checkAccountHasRoleStep,
        HasRoleResponseStep hasRoleResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountHasRoleStep = checkAccountHasRoleStep;
        this.hasRoleResponseStep = hasRoleResponseStep;
    }

    @PostMapping("has-role")
    public ResponseEntity<Response<HasRoleData, HasRoleMetadata>> hasRole(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<HasRoleData, HasRoleMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountHasRoleStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, hasRoleResponseStep);
    }
}
