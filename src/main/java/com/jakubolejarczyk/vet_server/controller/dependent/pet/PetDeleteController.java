package com.jakubolejarczyk.vet_server.controller.dependent.pet;

import com.jakubolejarczyk.vet_server.dto.data.dependent.PetData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.PetMetadata;
import com.jakubolejarczyk.vet_server.dto.request.logic.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.delete.DeletePetStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.pet.PetCreateResponseStep;
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

public class PetDeleteController extends StepRunnerController<PetData, PetMetadata> {
    private final GetAccountByTokenStep<PetData, PetMetadata> getAccountByTokenStep;
    private final DeletePetStep<PetData, PetMetadata> deletePetStep;
    private final PetCreateResponseStep petReadResponseStep;

    public PetDeleteController(
        ObjectFactory<StepStore<PetData, PetMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<PetData, PetMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<PetData, PetMetadata> getAccountByTokenStep,
        DeletePetStep<PetData, PetMetadata> deletePetStep,
        PetCreateResponseStep petReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.deletePetStep = deletePetStep;
        this.petReadResponseStep = petReadResponseStep;
    }

    @PostMapping("pet-delete")
    public ResponseEntity<Response<PetData, PetMetadata>> petDelete(
        @Valid @RequestBody DeleteRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<PetData, PetMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(deletePetStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("petIds", request.getIds());
        return runController(steps, petReadResponseStep);
    }
}
