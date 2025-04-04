package com.jakubolejarczyk.vet_server.controller.pet;

import com.jakubolejarczyk.vet_server.dto.request.logic.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessDeletePetStepRunner;
import com.jakubolejarczyk.vet_server.step.update.UpdatePetIsArchivedStepRunner;
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
public class PetDeleteController extends StepRunnerController {
    private final UpdatePetIsArchivedStepRunner updatePetIsArchivedStep;
    private final SuccessDeletePetStepRunner successDeletePetStep;

    public PetDeleteController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            UpdatePetIsArchivedStepRunner updatePetIsArchivedStep,
            SuccessDeletePetStepRunner successDeletePetStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.updatePetIsArchivedStep = updatePetIsArchivedStep;
        this.successDeletePetStep = successDeletePetStep;
    }

    @PostMapping("pet-delete")
    public ResponseEntity<Response<?, ?>> petDelete(@Valid @RequestBody DeleteRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(updatePetIsArchivedStep);
        steps.addLast(successDeletePetStep);
        String[] dataKeys = {};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("petIds", request.getIds());
        return runController(steps);
    }
}
