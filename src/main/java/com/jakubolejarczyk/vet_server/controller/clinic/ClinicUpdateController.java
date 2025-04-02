package com.jakubolejarczyk.vet_server.controller.clinic;

import com.jakubolejarczyk.vet_server.dto.request.clinic.ClinicUpdateRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.GetEmploymentByAccountIdAndClinicIdAndIsOwnerStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessUpdateClinicStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateClinicByEmploymentStep;
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
public class ClinicUpdateController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final GetEmploymentByAccountIdAndClinicIdAndIsOwnerStep getEmploymentByAccountIdAndClinicIdAndIsOwnerStep;
    private final UpdateClinicByEmploymentStep updateClinicByEmploymentStep;
    private final SuccessUpdateClinicStep successUpdateClinicStep;

    public ClinicUpdateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            GetEmploymentByAccountIdAndClinicIdAndIsOwnerStep getEmploymentByAccountIdAndClinicIdAndIsOwnerStep,
            UpdateClinicByEmploymentStep updateClinicByEmploymentStep,
            SuccessUpdateClinicStep successUpdateClinicStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentByAccountIdAndClinicIdAndIsOwnerStep = getEmploymentByAccountIdAndClinicIdAndIsOwnerStep;
        this.updateClinicByEmploymentStep = updateClinicByEmploymentStep;
        this.successUpdateClinicStep = successUpdateClinicStep;
    }

    @PostMapping("clinic-update")
    public ResponseEntity<Response<?, ?>> clinicRead(@RequestBody ClinicUpdateRequest request) {
        val steps = new ArrayList<StepModel>();
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
                .entityName(request.getEntityName())
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
