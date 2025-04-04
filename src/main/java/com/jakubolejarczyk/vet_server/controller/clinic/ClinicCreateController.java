package com.jakubolejarczyk.vet_server.controller.clinic;

import com.jakubolejarczyk.vet_server.dto.request.dependent.ClinicRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.create.CreateClinicStepRunner;
import com.jakubolejarczyk.vet_server.step.create.CreateEmploymentStepRunner;
import com.jakubolejarczyk.vet_server.step.create.CreateOpeningHoursStepRunner;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessCreateClinicStepRunner;
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
public class ClinicCreateController extends StepRunnerController {
    private final GetAccountByTokenStepRunner getAccountByTokenStep;
    private final CreateOpeningHoursStepRunner createOpeningHoursStep;
    private final CreateClinicStepRunner createClinicStep;
    private final CreateEmploymentStepRunner createEmploymentStep;
    private final SuccessCreateClinicStepRunner successCreateClinicStep;

    public ClinicCreateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            CreateOpeningHoursStepRunner createOpeningHoursStep,
            CreateClinicStepRunner createClinicStep,
            CreateEmploymentStepRunner createEmploymentStep,
            SuccessCreateClinicStepRunner successCreateClinicStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.createOpeningHoursStep = createOpeningHoursStep;
        this.createClinicStep = createClinicStep;
        this.createEmploymentStep = createEmploymentStep;
        this.successCreateClinicStep = successCreateClinicStep;
    }

    @PostMapping("clinic-create")
    public ResponseEntity<Response<?, ?>> clinicCreate(@Valid @RequestBody ClinicRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(createOpeningHoursStep);
        steps.addLast(createClinicStep);
        steps.addLast(createEmploymentStep);
        steps.addLast(successCreateClinicStep);
        String[] dataKeys = {};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val requestClinic = Clinic.builder()
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
        getStepStore().setItem("requestClinic", requestClinic);
        return runController(steps);
    }
}
