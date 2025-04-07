package com.jakubolejarczyk.vet_server.controller.dependent.opening_hour;

import com.jakubolejarczyk.vet_server.dto.data.independent.OpeningHourData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.OpeningHourMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.clinic.GetClinicsByEmploymentsStep;
import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentsByAccountStep;
import com.jakubolejarczyk.vet_server.step.get.opening_hour.GetOpeningHoursByClinicsStep;
import com.jakubolejarczyk.vet_server.step.metadata.OpeningHourMetadataStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.opening_hour.OpeningHourReadResponseStep;
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
public class OpeningHourReadController extends StepRunnerController<OpeningHourData, OpeningHourMetadata> {
    private final GetAccountByTokenStep<OpeningHourData, OpeningHourMetadata> getAccountByTokenStep;
    private final GetEmploymentsByAccountStep<OpeningHourData, OpeningHourMetadata> getEmploymentsByAccountStep;
    private final GetClinicsByEmploymentsStep<OpeningHourData, OpeningHourMetadata> getClinicsByEmploymentsStep;
    private final GetOpeningHoursByClinicsStep<OpeningHourData, OpeningHourMetadata> getOpeningHoursByClinicsStep;
    private final OpeningHourMetadataStep openingHourMetadataStep;
    private final OpeningHourReadResponseStep openingHourReadResponseStep;

    public OpeningHourReadController(
        ObjectFactory<StepStore<OpeningHourData, OpeningHourMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<OpeningHourData, OpeningHourMetadata> getAccountByTokenStep,
        GetEmploymentsByAccountStep<OpeningHourData, OpeningHourMetadata> getEmploymentsByAccountStep,
        GetClinicsByEmploymentsStep<OpeningHourData, OpeningHourMetadata> getClinicsByEmploymentsStep,
        GetOpeningHoursByClinicsStep<OpeningHourData, OpeningHourMetadata> getOpeningHoursByClinicsStep,
        OpeningHourMetadataStep openingHourMetadataStep,
        OpeningHourReadResponseStep openingHourReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountStep = getEmploymentsByAccountStep;
        this.getClinicsByEmploymentsStep = getClinicsByEmploymentsStep;
        this.getOpeningHoursByClinicsStep = getOpeningHoursByClinicsStep;
        this.openingHourMetadataStep = openingHourMetadataStep;
        this.openingHourReadResponseStep = openingHourReadResponseStep;
    }

    @PostMapping("opening-hours-read")
    public ResponseEntity<Response<OpeningHourData, OpeningHourMetadata>> openingHoursRead(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<OpeningHourData, OpeningHourMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountStep);
        steps.addLast(getClinicsByEmploymentsStep);
        steps.addLast(getOpeningHoursByClinicsStep);
        steps.addLast(openingHourMetadataStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, openingHourReadResponseStep);
    }
}
