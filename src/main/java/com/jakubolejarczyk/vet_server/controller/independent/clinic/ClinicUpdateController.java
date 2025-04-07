package com.jakubolejarczyk.vet_server.controller.independent.clinic;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ClinicMetadata;
import com.jakubolejarczyk.vet_server.dto.request.independent.ClinicRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentByAccountAndClinicIdAndIsOwnerStep;
import com.jakubolejarczyk.vet_server.step.response.independent.clinic.ClinicUpdateResponseStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateClinicByEmploymentStep;
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
public class ClinicUpdateController extends StepRunnerController<ClinicData, ClinicMetadata> {
    private final GetAccountByTokenStep<ClinicData, ClinicMetadata> getAccountByTokenStep;
    private final GetEmploymentByAccountAndClinicIdAndIsOwnerStep<ClinicData, ClinicMetadata> getEmploymentByAccountAndClinicIdAndIsOwnerStep;
    private final UpdateClinicByEmploymentStep<ClinicData, ClinicMetadata> updateClinicByEmploymentStep;
    private final ClinicUpdateResponseStep clinicUpdateResponseStep;

    public ClinicUpdateController(
        ObjectFactory<StepStore<ClinicData, ClinicMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<ClinicData, ClinicMetadata> getAccountByTokenStep,
        GetEmploymentByAccountAndClinicIdAndIsOwnerStep<ClinicData, ClinicMetadata> getEmploymentByAccountAndClinicIdAndIsOwnerStep,
        UpdateClinicByEmploymentStep<ClinicData, ClinicMetadata> updateClinicByEmploymentStep,
        ClinicUpdateResponseStep clinicUpdateResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentByAccountAndClinicIdAndIsOwnerStep = getEmploymentByAccountAndClinicIdAndIsOwnerStep;
        this.updateClinicByEmploymentStep = updateClinicByEmploymentStep;
        this.clinicUpdateResponseStep = clinicUpdateResponseStep;
    }

    @PostMapping("clinic-update")
    public ResponseEntity<Response<ClinicData, ClinicMetadata>> clinicUpdate(
        @Valid @RequestBody ClinicRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<ClinicData, ClinicMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentByAccountAndClinicIdAndIsOwnerStep);
        steps.addLast(updateClinicByEmploymentStep);
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
        getStepStore().setItem("clinicId", clinicRequest.getId());
        return runController(steps, clinicUpdateResponseStep);
    }
}
