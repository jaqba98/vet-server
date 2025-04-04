package com.jakubolejarczyk.vet_server.controller.clinic;

import com.jakubolejarczyk.vet_server.dto.request.dependent.ClinicRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step.get.GetEmploymentByAccountIdAndClinicIdAndIsOwnerStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessUpdateClinicStepRunner;
import com.jakubolejarczyk.vet_server.step.update.UpdateClinicByEmploymentStepRunner;
import com.jakubolejarczyk.vet_server.store.StepStore;
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
public class ClinicUpdateController extends StepRunnerController {
    private final GetAccountByTokenStepRunner getAccountByTokenStep;
    private final GetEmploymentByAccountIdAndClinicIdAndIsOwnerStepRunner getEmploymentByAccountIdAndClinicIdAndIsOwnerStep;
    private final UpdateClinicByEmploymentStepRunner updateClinicByEmploymentStep;
    private final SuccessUpdateClinicStepRunner successUpdateClinicStep;

    public ClinicUpdateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            GetEmploymentByAccountIdAndClinicIdAndIsOwnerStepRunner getEmploymentByAccountIdAndClinicIdAndIsOwnerStep,
            UpdateClinicByEmploymentStepRunner updateClinicByEmploymentStep,
            SuccessUpdateClinicStepRunner successUpdateClinicStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentByAccountIdAndClinicIdAndIsOwnerStep = getEmploymentByAccountIdAndClinicIdAndIsOwnerStep;
        this.updateClinicByEmploymentStep = updateClinicByEmploymentStep;
        this.successUpdateClinicStep = successUpdateClinicStep;
    }

    @PostMapping("clinic-update")
    public ResponseEntity<Response<?, ?>> clinicRead(@RequestBody ClinicRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentByAccountIdAndClinicIdAndIsOwnerStep);
        steps.addLast(updateClinicByEmploymentStep);
        steps.addLast(successUpdateClinicStep);
        String[] dataKeys = {"updatedClinic"};
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
