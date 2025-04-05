package com.jakubolejarczyk.vet_server.controller.dependent.pet;

import com.jakubolejarczyk.vet_server.dto.data.dependent.PetData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.PetMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.GetClientsByEmploymentStep;
import com.jakubolejarczyk.vet_server.step.get.GetEmploymentsByAccountIdStep;
import com.jakubolejarczyk.vet_server.step.get.GetPetsByClientsStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.pet.PetReadResponseStep;
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
public class PetReadController extends StepRunnerController<PetData, PetMetadata> {
    private final GetAccountByTokenStep<PetData, PetMetadata> getAccountByTokenStep;
    private final GetEmploymentsByAccountIdStep<PetData, PetMetadata> getEmploymentsByAccountIdStep;
    private final GetClientsByEmploymentStep<PetData, PetMetadata> getClientsByEmploymentStep;
    private final GetPetsByClientsStep<PetData, PetMetadata> getPetsByClientsStep;
    private final PetReadResponseStep petReadResponseStep;

    public PetReadController(
        ObjectFactory<StepStore<PetData, PetMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<PetData, PetMetadata> getAccountByTokenStep,
        GetEmploymentsByAccountIdStep<PetData, PetMetadata> getEmploymentsByAccountIdStep,
        GetClientsByEmploymentStep<PetData, PetMetadata> getClientsByEmploymentStep,
        GetPetsByClientsStep<PetData, PetMetadata> getPetsByClientsStep,
        PetReadResponseStep petReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountIdStep = getEmploymentsByAccountIdStep;
        this.getClientsByEmploymentStep = getClientsByEmploymentStep;
        this.getPetsByClientsStep = getPetsByClientsStep;
        this.petReadResponseStep = petReadResponseStep;
    }

    @PostMapping("pet-read")
    public ResponseEntity<Response<PetData, PetMetadata>> petRead(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<PetData, PetMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountIdStep);
        steps.addLast(getClientsByEmploymentStep);
        steps.addLast(getPetsByClientsStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, petReadResponseStep);
    }
}
