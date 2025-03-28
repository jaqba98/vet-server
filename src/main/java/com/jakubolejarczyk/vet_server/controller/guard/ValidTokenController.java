package com.jakubolejarczyk.vet_server.controller.guard;

import com.jakubolejarczyk.vet_server.service.base.BaseController;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.check.CheckTokenStep;
import com.jakubolejarczyk.vet_server.service.store.StepStore;
import jakarta.validation.Valid;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class ValidTokenController extends BaseController {
    public ValidTokenController(
            ObjectFactory<StepStore> stepStore,
            ObjectFactory<HandleValidationService> handleValidationService,
            CheckTokenStep checkTokenStep
    ) {
        super(
                stepStore,
                handleValidationService,
                new String[]{},
                new String[]{},
                new ArrayList<>()
        );
        steps.addLast(checkTokenStep);
    }

    @PostMapping("valid-token")
    public ResponseEntity<Response<?, ?>> runController(@Valid @RequestBody TokenRequest requestDto) {
        initController();
        stepStore.getObject().setItem("token", requestDto.getToken());
        return runController(steps);
    }
}
