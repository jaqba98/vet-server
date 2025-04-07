package com.jakubolejarczyk.vet_server.controller.dependent.service_clinic;

import com.jakubolejarczyk.vet_server.dto.data.dependent.ServiceClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.ServiceClinicMetadata;
import com.jakubolejarczyk.vet_server.dto.request.logic.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.delete.DeleteServiceClinicStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.service_clinic.ServiceClinicDeleteResponseStep;
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
public class ServiceClinicDeleteController extends StepRunnerController<ServiceClinicData, ServiceClinicMetadata> {
    private final GetAccountByTokenStep<ServiceClinicData, ServiceClinicMetadata> getAccountByTokenStep;
    private final DeleteServiceClinicStep<ServiceClinicData, ServiceClinicMetadata> deleteServiceClinicStep;
    private final ServiceClinicDeleteResponseStep serviceClinicDeleteResponseStep;

    public ServiceClinicDeleteController(
        ObjectFactory<StepStore<ServiceClinicData, ServiceClinicMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<ServiceClinicData, ServiceClinicMetadata> getAccountByTokenStep,
        DeleteServiceClinicStep<ServiceClinicData, ServiceClinicMetadata> deleteServiceClinicStep,
        ServiceClinicDeleteResponseStep serviceClinicDeleteResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.deleteServiceClinicStep = deleteServiceClinicStep;
        this.serviceClinicDeleteResponseStep = serviceClinicDeleteResponseStep;
    }

    @PostMapping("service-clinic-delete")
    public ResponseEntity<Response<ServiceClinicData, ServiceClinicMetadata>> serviceClinicDelete(
        @Valid @RequestBody DeleteRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<ServiceClinicData, ServiceClinicMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(deleteServiceClinicStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("serviceClinicIds", request.getIds());
        return runController(steps, serviceClinicDeleteResponseStep);
    }
}
