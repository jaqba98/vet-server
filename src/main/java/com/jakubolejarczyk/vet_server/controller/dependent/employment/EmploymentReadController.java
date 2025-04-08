package com.jakubolejarczyk.vet_server.controller.dependent.employment;

import com.jakubolejarczyk.vet_server.dto.data.dependent.EmploymentData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.EmploymentMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentsByAccountStep;
import com.jakubolejarczyk.vet_server.step.metadata.EmploymentMetadataStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.employment.EmploymentReadResponseStep;
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
public class EmploymentReadController extends StepRunnerController<EmploymentData, EmploymentMetadata> {
    private final GetAccountByTokenStep<EmploymentData, EmploymentMetadata> getAccountByTokenStep;
    private final GetEmploymentsByAccountStep<EmploymentData, EmploymentMetadata> getEmploymentsByAccountStep;
    private final EmploymentMetadataStep employmentMetadataStep;
    private final EmploymentReadResponseStep employmentReadResponseStep;

    public EmploymentReadController(
        ObjectFactory<StepStore<EmploymentData, EmploymentMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<EmploymentData, EmploymentMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<EmploymentData, EmploymentMetadata> getAccountByTokenStep,
        GetEmploymentsByAccountStep<EmploymentData, EmploymentMetadata> getEmploymentsByAccountStep,
        EmploymentMetadataStep employmentMetadataStep,
        EmploymentReadResponseStep employmentReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountStep = getEmploymentsByAccountStep;
        this.employmentMetadataStep = employmentMetadataStep;
        this.employmentReadResponseStep = employmentReadResponseStep;
    }

    @PostMapping("employment-read")
    public ResponseEntity<Response<EmploymentData, EmploymentMetadata>> employmentRead(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<EmploymentData, EmploymentMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountStep);
        steps.addLast(employmentMetadataStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, employmentReadResponseStep);
    }
}
