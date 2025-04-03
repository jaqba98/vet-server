package com.jakubolejarczyk.vet_server.controller.pet;

import com.jakubolejarczyk.vet_server.dto.request.pet.PetRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.check.CheckClientExistStep;
import com.jakubolejarczyk.vet_server.step.create.CreatePetStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessCreatePetStep;
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
public class PetCreateController extends BaseController {
    private final CheckClientExistStep checkClientExistStep;
    private final CreatePetStep createPetStep;
    private final SuccessCreatePetStep successCreatePetStep;

    public PetCreateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            CheckClientExistStep checkClientExistStep,
            CreatePetStep createPetStep,
            SuccessCreatePetStep successCreatePetStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.checkClientExistStep = checkClientExistStep;
        this.createPetStep = createPetStep;
        this.successCreatePetStep = successCreatePetStep;
    }

    @PostMapping("pet-create")
    public ResponseEntity<Response<?, ?>> petCreate(@Valid @RequestBody PetRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(checkClientExistStep);
        steps.addLast(createPetStep);
        steps.addLast(successCreatePetStep);
        String[] dataKeys = {};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val requestPet = Pet.builder()
                .id(request.getId())
                .entityName(request.getEntityName())
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
        getStepStore().setItem("requestPet", requestPet);
        getStepStore().setItem("clientId", requestPet.getClientId());
        return runController(steps);
    }
}
