package com.jakubolejarczyk.vet_server.controller.logic;

import com.jakubolejarczyk.vet_server.dto.data.logic.RegistrationData;
import com.jakubolejarczyk.vet_server.dto.metadata.logic.RegistrationMetadata;
import com.jakubolejarczyk.vet_server.dto.request.logic.RegistrationRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountNotExistByEmailStep;
import com.jakubolejarczyk.vet_server.step.check.CheckPasswordsMatchStep;
import com.jakubolejarczyk.vet_server.step.create.CreateAccountStep;
import com.jakubolejarczyk.vet_server.step.response.logic.RegistrationResponseStep;
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
public class RegistrationController extends StepRunnerController<RegistrationData, RegistrationMetadata> {
    private final CheckAccountNotExistByEmailStep<RegistrationData, RegistrationMetadata> checkAccountNotExistByEmailStep;
    private final CheckPasswordsMatchStep<RegistrationData, RegistrationMetadata> checkPasswordsMatchStep;
    private final CreateAccountStep<RegistrationData, RegistrationMetadata> createAccountStep;
    private final RegistrationResponseStep registrationResponseStep;

    public RegistrationController(
        ObjectFactory<StepStore<RegistrationData, RegistrationMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        CheckAccountNotExistByEmailStep<RegistrationData, RegistrationMetadata> checkAccountNotExistByEmailStep,
        CheckPasswordsMatchStep<RegistrationData, RegistrationMetadata> checkPasswordsMatchStep,
        CreateAccountStep<RegistrationData, RegistrationMetadata> createAccountStep,
        RegistrationResponseStep registrationResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.checkAccountNotExistByEmailStep = checkAccountNotExistByEmailStep;
        this.checkPasswordsMatchStep = checkPasswordsMatchStep;
        this.createAccountStep = createAccountStep;
        this.registrationResponseStep = registrationResponseStep;
    }

    @PostMapping("registration")
    public ResponseEntity<Response<RegistrationData, RegistrationMetadata>> registration(
        @Valid @RequestBody RegistrationRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<RegistrationData, RegistrationMetadata>>();
        steps.addLast(checkAccountNotExistByEmailStep);
        steps.addLast(checkPasswordsMatchStep);
        steps.addLast(createAccountStep);
        initController();
        getStepStore().setItem("email", request.getEmail());
        getStepStore().setItem("password", request.getPassword());
        getStepStore().setItem("confirmPassword", request.getConfirmPassword());
        getStepStore().setItem("firstName", request.getFirstName());
        getStepStore().setItem("lastName", request.getLastName());
        return runController(steps, registrationResponseStep);
    }
}
