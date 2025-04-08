package com.jakubolejarczyk.vet_server.controller.dependent.service_clinic;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ServiceClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ServiceClinicMetadata;
import com.jakubolejarczyk.vet_server.dto.request.dependent.ServiceClinicRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.ServiceClinic;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.service_clinic.ServiceClinicUpdateResponseStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateServiceClinicStep;
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
public class ServiceClinicUpdateController extends StepRunnerController<ServiceClinicData, ServiceClinicMetadata> {
    private final GetAccountByTokenStep<ServiceClinicData, ServiceClinicMetadata> getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStep<ServiceClinicData, ServiceClinicMetadata> checkAccountPermissionToClinicStep;
    private final UpdateServiceClinicStep<ServiceClinicData, ServiceClinicMetadata> updateServiceClinicStep;
    private final ServiceClinicUpdateResponseStep serviceClinicReadResponseStep;

    public ServiceClinicUpdateController(
        ObjectFactory<StepStore<ServiceClinicData, ServiceClinicMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<ServiceClinicData, ServiceClinicMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<ServiceClinicData, ServiceClinicMetadata> getAccountByTokenStep,
        CheckAccountPermissionToClinicStep<ServiceClinicData, ServiceClinicMetadata> checkAccountPermissionToClinicStep,
        UpdateServiceClinicStep<ServiceClinicData, ServiceClinicMetadata> updateServiceClinicStep,
        ServiceClinicUpdateResponseStep serviceClinicReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.updateServiceClinicStep = updateServiceClinicStep;
        this.serviceClinicReadResponseStep = serviceClinicReadResponseStep;
    }

    @PostMapping("service-clinic-update")
    public ResponseEntity<Response<ServiceClinicData, ServiceClinicMetadata>> serviceClinicUpdate(
        @Valid @RequestBody ServiceClinicRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<ServiceClinicData, ServiceClinicMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(updateServiceClinicStep);
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
