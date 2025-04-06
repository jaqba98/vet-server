package com.jakubolejarczyk.vet_server.controller.independent.clinic;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ClinicMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.clinic.GetClinicsByEmploymentsStep;
import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentsByAccountStep;
import com.jakubolejarczyk.vet_server.step.response.independent.ClinicReadResponseStep;
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
public class ClinicReadController extends StepRunnerController<ClinicData, ClinicMetadata> {
    private final GetAccountByTokenStep<ClinicData, ClinicMetadata> getAccountByTokenStep;
    private final GetEmploymentsByAccountStep<ClinicData, ClinicMetadata> getEmploymentsByAccountStep;
    private final GetClinicsByEmploymentsStep<ClinicData, ClinicMetadata> getClinicsByEmploymentsStep;
    private final ClinicReadResponseStep clinicReadResponseStep;

    public ClinicReadController(
        ObjectFactory<StepStore<ClinicData, ClinicMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<ClinicData, ClinicMetadata> getAccountByTokenStep,
        GetEmploymentsByAccountStep<ClinicData, ClinicMetadata> getEmploymentsByAccountStep,
        GetClinicsByEmploymentsStep<ClinicData, ClinicMetadata> getClinicsByEmploymentsStep,
        ClinicReadResponseStep clinicReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountStep = getEmploymentsByAccountStep;
        this.getClinicsByEmploymentsStep = getClinicsByEmploymentsStep;
        this.clinicReadResponseStep = clinicReadResponseStep;
    }

    @PostMapping("clinic-read")
    public ResponseEntity<Response<ClinicData, ClinicMetadata>> clinicRead(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<ClinicData, ClinicMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountStep);
        steps.addLast(getClinicsByEmploymentsStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, clinicReadResponseStep);
    }
}
