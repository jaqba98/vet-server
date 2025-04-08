package com.jakubolejarczyk.vet_server.controller.logic;

import com.jakubolejarczyk.vet_server.dto.data.logic.LogoutData;
import com.jakubolejarczyk.vet_server.dto.metadata.logic.LogoutMetadata;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.response.logic.LogoutResponseStep;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class LogoutController extends StepRunnerController<LogoutData, LogoutMetadata> {
    private final LogoutResponseStep logoutResponseStep;

    public LogoutController(
        ObjectFactory<StepStore<LogoutData, LogoutMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<LogoutData, LogoutMetadata>> handleValidationServiceObjectFactory,
        LogoutResponseStep logoutResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.logoutResponseStep = logoutResponseStep;
    }

    @PostMapping("logout")
    public ResponseEntity<Response<LogoutData, LogoutMetadata>> logout() {
        val steps = new ArrayList<StepRunnerModel<LogoutData, LogoutMetadata>>();
        initController();
        return runController(steps, logoutResponseStep);
    }
}
