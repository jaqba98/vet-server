package com.jakubolejarczyk.vet_server.controller.dependent.service_clinic;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ServiceClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ServiceClinicMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.clinic.GetClinicsByEmploymentsStep;
import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentsByAccountStep;
import com.jakubolejarczyk.vet_server.step.get.service_clinic.GetServiceClinicsByClinicsStep;
import com.jakubolejarczyk.vet_server.step.metadata.ServiceClinicMetadataStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.service_clinic.ServiceClinicReadResponseStep;
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
public class ServiceClinicReadController extends StepRunnerController<ServiceClinicData, ServiceClinicMetadata> {
    private final GetAccountByTokenStep<ServiceClinicData, ServiceClinicMetadata> getAccountByTokenStep;
    private final GetEmploymentsByAccountStep<ServiceClinicData, ServiceClinicMetadata> getEmploymentsByAccountStep;
    private final GetClinicsByEmploymentsStep<ServiceClinicData, ServiceClinicMetadata> getClinicsByEmploymentsStep;
    private final GetServiceClinicsByClinicsStep<ServiceClinicData, ServiceClinicMetadata> getServiceClinicsByClinicsStep;
    private final ServiceClinicMetadataStep serviceClinicMetadataStep;
    private final ServiceClinicReadResponseStep serviceClinicReadResponseStep;

    public ServiceClinicReadController(
        ObjectFactory<StepStore<ServiceClinicData, ServiceClinicMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<ServiceClinicData, ServiceClinicMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<ServiceClinicData, ServiceClinicMetadata> getAccountByTokenStep,
        GetEmploymentsByAccountStep<ServiceClinicData, ServiceClinicMetadata> getEmploymentsByAccountStep,
        GetClinicsByEmploymentsStep<ServiceClinicData, ServiceClinicMetadata> getClinicsByEmploymentsStep,
        GetServiceClinicsByClinicsStep<ServiceClinicData, ServiceClinicMetadata> getServiceClinicsByClinicsStep,
        ServiceClinicMetadataStep serviceClinicMetadataStep,
        ServiceClinicReadResponseStep serviceClinicReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountStep = getEmploymentsByAccountStep;
        this.getClinicsByEmploymentsStep = getClinicsByEmploymentsStep;
        this.getServiceClinicsByClinicsStep = getServiceClinicsByClinicsStep;
        this.serviceClinicMetadataStep = serviceClinicMetadataStep;
        this.serviceClinicReadResponseStep = serviceClinicReadResponseStep;
    }

    @PostMapping("service-clinic-read")
    public ResponseEntity<Response<ServiceClinicData, ServiceClinicMetadata>> serviceClinicRead(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<ServiceClinicData, ServiceClinicMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountStep);
        steps.addLast(getClinicsByEmploymentsStep);
        steps.addLast(getServiceClinicsByClinicsStep);
        steps.addLast(serviceClinicMetadataStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, serviceClinicReadResponseStep);
    }
}
