package com.jakubolejarczyk.vet_server.controller.clinic;

import com.jakubolejarczyk.vet_server.dto.request.clinic.ClinicCreateRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.create.CreateClinicStep;
import com.jakubolejarczyk.vet_server.step.create.CreateEmploymentStep;
import com.jakubolejarczyk.vet_server.step.create.CreateOpeningHoursStep;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
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
public class ClinicCreateController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final CreateOpeningHoursStep createOpeningHoursStep;
    private final CreateClinicStep createClinicStep;
    private final CreateEmploymentStep createEmploymentStep;

    public ClinicCreateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            CreateOpeningHoursStep createOpeningHoursStep,
            CreateClinicStep createClinicStep,
            CreateEmploymentStep createEmploymentStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.createOpeningHoursStep = createOpeningHoursStep;
        this.createClinicStep = createClinicStep;
        this.createEmploymentStep = createEmploymentStep;
    }

    @PostMapping("clinic-create")
    public ResponseEntity<Response<?, ?>> clinicCreate(@RequestBody ClinicCreateRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(createOpeningHoursStep);
        steps.addLast(createClinicStep);
        steps.addLast(createEmploymentStep);
        String[] dataKeys = {};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("name", request.getName());
        getStepStore().setItem("street", request.getStreet());
        getStepStore().setItem("buildingNumber", request.getBuildingNumber());
        getStepStore().setItem("apartmentNumber", request.getApartmentNumber());
        getStepStore().setItem("postalCode", request.getPostalCode());
        getStepStore().setItem("city", request.getCity());
        getStepStore().setItem("province", request.getProvince());
        getStepStore().setItem("country", request.getCountry());
        getStepStore().setItem("email", request.getEmail());
        getStepStore().setItem("phoneNumber", request.getPhoneNumber());
        return runController(steps);
    }
}
