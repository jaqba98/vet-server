package com.jakubolejarczyk.vet_server.controller.dependent.pet;

import com.jakubolejarczyk.vet_server.dto.data.dependent.PetData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.PetMetadata;
import com.jakubolejarczyk.vet_server.dto.request.dependent.PetRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClientStep;
import com.jakubolejarczyk.vet_server.step.create.CreatePetStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.client.GetClientByPetRequestStep;
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
public class PetCreateController extends StepRunnerController<PetData, PetMetadata> {
    private final GetAccountByTokenStep<PetData, PetMetadata> getAccountByTokenStep;
    private final GetClientByPetRequestStep<PetData, PetMetadata> getClientByPetRequestStep;
    private final CheckAccountPermissionToClientStep<PetData, PetMetadata> checkAccountPermissionToClientStep;
    private final CreatePetStep<PetData, PetMetadata> createPetStep;
    private final PetCreateResponseStep petReadResponseStep;

    public PetCreateController(
        ObjectFactory<StepStore<PetData, PetMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<PetData, PetMetadata> getAccountByTokenStep,
        GetClientByPetRequestStep<PetData, PetMetadata> getClientByPetRequestStep,
        CheckAccountPermissionToClientStep<PetData, PetMetadata> checkAccountPermissionToClientStep,
        CreatePetStep<PetData, PetMetadata> createPetStep,
        PetCreateResponseStep petReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getClientByPetRequestStep = getClientByPetRequestStep;
        this.checkAccountPermissionToClientStep = checkAccountPermissionToClientStep;
        this.createPetStep = createPetStep;
        this.petReadResponseStep = petReadResponseStep;
    }

    @PostMapping("pet-create")
    public ResponseEntity<Response<PetData, PetMetadata>> petCreate(
        @Valid @RequestBody PetRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<PetData, PetMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getClientByPetRequestStep);
        steps.addLast(checkAccountPermissionToClientStep);
        steps.addLast(createPetStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        val petRequest = Pet.builder()
            .id(request.getId())
            .fullName(request.getFullName())
            .species(request.getSpecies())
            .breed(request.getBreed())
            .dateOfBirth(request.getDateOfBirth())
            .weightKg(request.getWeightKg())
            .color(request.getColor())
            .sterilized(request.getSterilized())
            .pictureUrl(request.getPictureUrl())
            .microchipNumber(request.getMicrochipNumber())
            .medicalNotes(request.getMedicalNotes())
            .clientId(request.getClientId())
            .build();
        getStepStore().setItem("petRequest", petRequest);
        return runController(steps, petReadResponseStep);
    }
}

