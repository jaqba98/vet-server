package com.jakubolejarczyk.vet_server.controller.dependent.pet;

import com.jakubolejarczyk.vet_server.dto.data.dependent.PetData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.PetMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.client.GetClientsByClinicsStep;
import com.jakubolejarczyk.vet_server.step.get.clinic.GetClinicsByEmploymentsStep;
import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentsByAccountStep;
import com.jakubolejarczyk.vet_server.step.get.pet.GetPetsByClientsStep;
import com.jakubolejarczyk.vet_server.step.metadata.PetMetadataStep;
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
    private final GetEmploymentsByAccountStep<PetData, PetMetadata> getEmploymentsByAccountStep;
    private final GetClinicsByEmploymentsStep<PetData, PetMetadata> getClinicsByEmploymentsStep;
    private final GetClientsByClinicsStep<PetData, PetMetadata> getClientsByClinicsStep;
    private final GetPetsByClientsStep<PetData, PetMetadata> getPetsByClientsStep;
    private final PetMetadataStep petMetadataStep;
    private final PetReadResponseStep petReadResponseStep;

    public PetReadController(
        ObjectFactory<StepStore<PetData, PetMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<PetData, PetMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<PetData, PetMetadata> getAccountByTokenStep,
        GetEmploymentsByAccountStep<PetData, PetMetadata> getEmploymentsByAccountStep,
        GetClinicsByEmploymentsStep<PetData, PetMetadata> getClinicsByEmploymentsStep,
        GetClientsByClinicsStep<PetData, PetMetadata> getClientsByClinicsStep,
        GetPetsByClientsStep<PetData, PetMetadata> getPetsByClientsStep,
        PetMetadataStep petMetadataStep,
        PetReadResponseStep petReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountStep = getEmploymentsByAccountStep;
        this.getClinicsByEmploymentsStep = getClinicsByEmploymentsStep;
        this.getClientsByClinicsStep = getClientsByClinicsStep;
        this.getPetsByClientsStep = getPetsByClientsStep;
        this.petMetadataStep = petMetadataStep;
        this.petReadResponseStep = petReadResponseStep;
    }

    @PostMapping("pet-read")
    public ResponseEntity<Response<PetData, PetMetadata>> petRead(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<PetData, PetMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountStep);
        steps.addLast(getClinicsByEmploymentsStep);
        steps.addLast(getClientsByClinicsStep);
        steps.addLast(getPetsByClientsStep);
        steps.addLast(petMetadataStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, petReadResponseStep);
    }
}
