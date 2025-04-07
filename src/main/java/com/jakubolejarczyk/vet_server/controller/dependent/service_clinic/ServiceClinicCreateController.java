package com.jakubolejarczyk.vet_server.controller.dependent.service_clinic;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ServiceClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ServiceClinicMetadata;
import com.jakubolejarczyk.vet_server.dto.request.dependent.ServiceClinicRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStep;
import com.jakubolejarczyk.vet_server.step.create.CreateServiceClinicStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.service_clinic.ServiceClinicCreateResponseStep;
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
public class ServiceClinicCreateController extends StepRunnerController<ServiceClinicData, ServiceClinicMetadata> {
    private final GetAccountByTokenStep<ServiceClinicData, ServiceClinicMetadata> getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStep<ServiceClinicData, ServiceClinicMetadata> checkAccountPermissionToClinicStep;
    private final CreateServiceClinicStep<ServiceClinicData, ServiceClinicMetadata> createServiceClinicStep;
    private final ServiceClinicCreateResponseStep serviceClinicReadResponseStep;

    public ServiceClinicCreateController(
        ObjectFactory<StepStore<ServiceClinicData, ServiceClinicMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<ServiceClinicData, ServiceClinicMetadata> getAccountByTokenStep,
        CheckAccountPermissionToClinicStep<ServiceClinicData, ServiceClinicMetadata> checkAccountPermissionToClinicStep,
        CreateServiceClinicStep<ServiceClinicData, ServiceClinicMetadata> createServiceClinicStep,
        ServiceClinicCreateResponseStep serviceClinicReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.createServiceClinicStep = createServiceClinicStep;
        this.serviceClinicReadResponseStep = serviceClinicReadResponseStep;
    }

    @PostMapping("service-clinic-create")
    public ResponseEntity<Response<ServiceClinicData, ServiceClinicMetadata>> serviceClinicCreate(
        @Valid @RequestBody ServiceClinicRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<ServiceClinicData, ServiceClinicMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(createServiceClinicStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        val serviceClinicRequest = ServiceClinic.builder()
            .id(request.getId())
            .fullName(request.getFullName())
            .description(request.getDescription())
            .category(request.getCategory())
            .durationMinutes(request.getDurationMinutes())
            .price(request.getPrice())
            .isAvailable(request.getIsAvailable())
            .clinicId(request.getClinicId())
            .build();
        getStepStore().setItem("serviceClinicRequest", serviceClinicRequest);
        getStepStore().setItem("clinicId", request.getClinicId());
        return runController(steps, serviceClinicReadResponseStep);
    }
}
