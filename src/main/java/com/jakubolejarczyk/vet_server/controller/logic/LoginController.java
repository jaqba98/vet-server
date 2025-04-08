package com.jakubolejarczyk.vet_server.controller.logic;

import com.jakubolejarczyk.vet_server.dto.data.logic.LoginData;
import com.jakubolejarczyk.vet_server.dto.metadata.logic.LoginMetadata;
import com.jakubolejarczyk.vet_server.dto.request.logic.LoginRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetTokenByAccountEmailAndPasswordStep;
import com.jakubolejarczyk.vet_server.step.response.logic.LoginResponseStep;
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
public class LoginController extends StepRunnerController<LoginData, LoginMetadata> {
    private final GetTokenByAccountEmailAndPasswordStep<LoginData, LoginMetadata> getTokenByAccountEmailAndPasswordStep;
    private final LoginResponseStep loginResponseStep;

    public LoginController(
        ObjectFactory<StepStore<LoginData, LoginMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<LoginData, LoginMetadata>> handleValidationServiceObjectFactory,
        GetTokenByAccountEmailAndPasswordStep<LoginData, LoginMetadata> getTokenByAccountEmailAndPasswordStep,
        LoginResponseStep loginResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getTokenByAccountEmailAndPasswordStep = getTokenByAccountEmailAndPasswordStep;
        this.loginResponseStep = loginResponseStep;
    }

    @PostMapping("login")
    public ResponseEntity<Response<LoginData, LoginMetadata>> login(@Valid @RequestBody LoginRequest request) {
        val steps = new ArrayList<StepRunnerModel<LoginData, LoginMetadata>>();
        steps.addLast(getTokenByAccountEmailAndPasswordStep);
        initController();
        getStepStore().setItem("email", request.getEmail());
        getStepStore().setItem("password", request.getPassword());
        return runController(steps, loginResponseStep);
    }
}
