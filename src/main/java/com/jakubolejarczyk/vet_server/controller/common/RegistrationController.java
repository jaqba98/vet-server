package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.service.base.BaseController;
import com.jakubolejarczyk.vet_server.dto.request.common.RegistrationRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.create.CreateAccountStep;
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
public class RegistrationController extends BaseController {
    public RegistrationController(
            ObjectFactory<StepStore> stepStore,
            ObjectFactory<HandleValidationService> handleValidationService,
            CreateAccountStep createAccountStep
    ) {
        super(
                stepStore,
                handleValidationService,
                new String[]{},
                new String[]{},
                new ArrayList<>()
        );
        steps.addLast(createAccountStep);
    }

    @PostMapping("registration")
    public ResponseEntity<Response<?, ?>> runController(@Valid @RequestBody RegistrationRequest requestDto) {
        initController();
        stepStore.getObject().setItem("email", requestDto.getEmail());
        stepStore.getObject().setItem("password", requestDto.getPassword());
        stepStore.getObject().setItem("firstName", requestDto.getFirstName());
        stepStore.getObject().setItem("lastName", requestDto.getLastName());
        return runController(steps);
    }
}
