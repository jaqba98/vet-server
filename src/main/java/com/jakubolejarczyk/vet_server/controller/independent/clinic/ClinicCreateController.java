package com.jakubolejarczyk.vet_server.controller.independent.clinic;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ClinicMetadata;
import com.jakubolejarczyk.vet_server.dto.request.independent.ClinicRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.create.CreateClinicStep;
import com.jakubolejarczyk.vet_server.step.create.CreateEmploymentStep;
import com.jakubolejarczyk.vet_server.step.create.CreateOpeningHourStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.independent.clinic.ClinicCreateResponseStep;
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
public class ClinicCreateController extends StepRunnerController<ClinicData, ClinicMetadata> {
    private final GetAccountByTokenStep<ClinicData, ClinicMetadata> getAccountByTokenStep;
    private final CreateClinicStep<ClinicData, ClinicMetadata> createClinicStep;
    private final CreateOpeningHourStep<ClinicData, ClinicMetadata> createOpeningHourStep;
    private final CreateEmploymentStep<ClinicData, ClinicMetadata> createEmploymentStep;
    private final ClinicCreateResponseStep clinicCreateResponseStep;

    public ClinicCreateController(
        ObjectFactory<StepStore<ClinicData, ClinicMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<ClinicData, ClinicMetadata> getAccountByTokenStep,
        CreateClinicStep<ClinicData, ClinicMetadata> createClinicStep,
        CreateOpeningHourStep<ClinicData, ClinicMetadata> createOpeningHourStep,
        CreateEmploymentStep<ClinicData, ClinicMetadata> createEmploymentStep,
        ClinicCreateResponseStep clinicCreateResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.createClinicStep = createClinicStep;
        this.createOpeningHourStep = createOpeningHourStep;
        this.createEmploymentStep = createEmploymentStep;
        this.clinicCreateResponseStep = clinicCreateResponseStep;
    }

    @PostMapping("clinic-create")
    public ResponseEntity<Response<ClinicData, ClinicMetadata>> clinicCreate(
        @Valid @RequestBody ClinicRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<ClinicData, ClinicMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(createClinicStep);
        steps.addLast(createOpeningHourStep);
        steps.addLast(createEmploymentStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        val clinicRequest = Clinic.builder()
            .id(request.getId())
            .fullName(request.getFullName())
            .street(request.getStreet())
            .buildingNumber(request.getBuildingNumber())
            .apartmentNumber(request.getApartmentNumber())
            .postalCode(request.getPostalCode())
            .city(request.getCity())
            .province(request.getProvince())
            .country(request.getCountry())
            .email(request.getEmail())
            .phoneNumber(request.getPhoneNumber())
            .build();
        getStepStore().setItem("clinicRequest", clinicRequest);
        return runController(steps, clinicCreateResponseStep);
    }
}
