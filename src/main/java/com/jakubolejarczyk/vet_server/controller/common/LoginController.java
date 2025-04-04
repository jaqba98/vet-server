package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.logic.LoginRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.get.GetTokenByLoginDetailsStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
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
public class LoginController extends StepRunnerController {
    private final GetTokenByLoginDetailsStepRunner getTokenByLoginDetailsStep;

    public LoginController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetTokenByLoginDetailsStepRunner getTokenByLoginDetailsStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getTokenByLoginDetailsStep = getTokenByLoginDetailsStep;
    }

    @PostMapping("login")
    public ResponseEntity<Response<?, ?>> login(@RequestBody LoginRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(getTokenByLoginDetailsStep);
        String[] dataKeys = {"token"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("email", request.getEmail());
        getStepStore().setItem("password", request.getPassword());
        return runController(steps);
    }
}
