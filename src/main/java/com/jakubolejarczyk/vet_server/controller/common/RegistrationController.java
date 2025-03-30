package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.common.RegistrationRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountNotExistStep;
import com.jakubolejarczyk.vet_server.step.create.CreateAccountStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
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
public class RegistrationController extends BaseController {
    private final CheckAccountNotExistStep checkAccountNotExistStep;
    private final CreateAccountStep createAccountStep;

    public RegistrationController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            CheckAccountNotExistStep checkAccountNotExistStep,
            CreateAccountStep createAccountStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.checkAccountNotExistStep = checkAccountNotExistStep;
        this.createAccountStep = createAccountStep;
    }

    @PostMapping("registration")
    public ResponseEntity<Response<?, ?>> registration(@Valid @RequestBody RegistrationRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(checkAccountNotExistStep);
        steps.addLast(createAccountStep);
        String[] dataKeys = {};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("email", request.getEmail());
        getStepStore().setItem("password", request.getPassword());
        getStepStore().setItem("firstName", request.getFirstName());
        getStepStore().setItem("lastName", request.getLastName());
        return runController(steps);
    }
}
