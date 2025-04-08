package com.jakubolejarczyk.vet_server.controller.dependent.vet;

import com.jakubolejarczyk.vet_server.dto.data.dependent.VetData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.VetMetadata;
import com.jakubolejarczyk.vet_server.dto.request.dependent.VetRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.vet.VetUpdateResponseStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateVetStep;
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
public class VetUpdateController extends StepRunnerController<VetData, VetMetadata> {
    private final GetAccountByTokenStep<VetData, VetMetadata> getAccountByTokenStep;
    private final UpdateVetStep<VetData, VetMetadata> updateVetStep;
    private final VetUpdateResponseStep vetUpdateResponseStep;

    public VetUpdateController(
        ObjectFactory<StepStore<VetData, VetMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<VetData, VetMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<VetData, VetMetadata> getAccountByTokenStep,
        UpdateVetStep<VetData, VetMetadata> updateVetStep,
        VetUpdateResponseStep vetUpdateResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.updateVetStep = updateVetStep;
        this.vetUpdateResponseStep = vetUpdateResponseStep;
    }

    @PostMapping("vet-update")
    public ResponseEntity<Response<VetData, VetMetadata>> vetRead(
        @Valid @RequestBody VetRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<VetData, VetMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(updateVetStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        val vetRequest = Vet.builder()
            .id(request.getId())
            .licenseNumber(request.getLicenseNumber())
            .licenseIssueDate(request.getLicenseIssueDate())
            .licenseExpiryDate(request.getLicenseExpiryDate())
            .specialization(request.getSpecialization())
            .yearsOfExperience(request.getYearsOfExperience())
            .accountId(request.getAccountId())
            .build();
        getStepStore().setItem("vetRequest", vetRequest);
        return runController(steps, vetUpdateResponseStep);
    }
}
