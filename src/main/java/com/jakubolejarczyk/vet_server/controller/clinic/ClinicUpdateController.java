package com.jakubolejarczyk.vet_server.controller.clinic;

import com.jakubolejarczyk.vet_server.dto.request.clinic.ClinicUpdateRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStep;
import com.jakubolejarczyk.vet_server.step.get.GetClinicStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.update.UpdateClinicStep;
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
    private final CheckAccountPermissionToClinicStep checkAccountPermissionToClinicStep;
    private final GetClinicStep getClinicStep;
    private final UpdateClinicStep updateClinicStep;

    public ClinicUpdateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            CheckAccountPermissionToClinicStep checkAccountPermissionToClinicStep,
            GetClinicStep getClinicStep,
            UpdateClinicStep updateClinicStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.getClinicStep = getClinicStep;
        this.updateClinicStep = updateClinicStep;
    }

    @PostMapping("clinic-update")
    public ResponseEntity<Response<?, ?>> clinicRead(@RequestBody ClinicUpdateRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(getClinicStep);
        steps.addLast(updateClinicStep);
        String[] dataKeys = {"clinic"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val newClinic = Clinic.builder()
                .id(request.getId())
                .isArchived(request.getIsArchived())
                .name(request.getName())
                .street(request.getStreet())
                .buildingNumber(request.getBuildingNumber())
                .apartmentNumber(request.getApartmentNumber())
                .postalCode(request.getPostalCode())
                .city(request.getCity())
                .province(request.getProvince())
                .country(request.getCountry())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .openingHoursId(null)
                .build();
        getStepStore().setItem("newClinic", newClinic);
        getStepStore().setItem("clinicId", newClinic.getId());
        return runController(steps);
    }
}
