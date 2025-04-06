package com.jakubolejarczyk.vet_server.controller.guard;

import com.jakubolejarczyk.vet_server.dto.data.guard.GetAccountData;
import com.jakubolejarczyk.vet_server.dto.metadata.guard.GetAccountMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.guard.GetAccountResponseStep;
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
public class GetAccountController extends StepRunnerController<GetAccountData, GetAccountMetadata> {
    private final GetAccountByTokenStep<GetAccountData, GetAccountMetadata> getAccountByTokenStep;
    private final GetAccountResponseStep getAccountResponseStep;

    public GetAccountController(
        ObjectFactory<StepStore<GetAccountData, GetAccountMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<GetAccountData, GetAccountMetadata> getAccountByTokenStep,
        GetAccountResponseStep getAccountResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getAccountResponseStep = getAccountResponseStep;
    }

    @PostMapping("get-account")
    public ResponseEntity<Response<GetAccountData, GetAccountMetadata>> getAccount(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<GetAccountData, GetAccountMetadata>>();
        steps.addLast(getAccountByTokenStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, getAccountResponseStep);
    }
}
