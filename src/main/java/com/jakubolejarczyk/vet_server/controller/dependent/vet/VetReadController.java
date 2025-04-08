package com.jakubolejarczyk.vet_server.controller.dependent.vet;

import com.jakubolejarczyk.vet_server.dto.data.dependent.VetData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.VetMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.vet.GetVetByAccountStep;
import com.jakubolejarczyk.vet_server.step.metadata.VetMetadataStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.vet.VetReadResponseStep;
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
public class VetReadController extends StepRunnerController<VetData, VetMetadata> {
    private final GetAccountByTokenStep<VetData, VetMetadata> getAccountByTokenStep;
    private final GetVetByAccountStep<VetData, VetMetadata> getVetByAccountStep;
    private final VetMetadataStep vetMetadataStep;
    private final VetReadResponseStep vetReadResponseStep;

    public VetReadController(
        ObjectFactory<StepStore<VetData, VetMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<VetData, VetMetadata> getAccountByTokenStep,
        GetVetByAccountStep<VetData, VetMetadata> getVetByAccountStep,
        VetMetadataStep vetMetadataStep,
        VetReadResponseStep vetReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getVetByAccountStep = getVetByAccountStep;
        this.vetMetadataStep = vetMetadataStep;
        this.vetReadResponseStep = vetReadResponseStep;
    }

    @PostMapping("vet-read")
    public ResponseEntity<Response<VetData, VetMetadata>> vetRead(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<VetData, VetMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getVetByAccountStep);
        steps.addLast(vetMetadataStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, vetReadResponseStep);
    }
}
