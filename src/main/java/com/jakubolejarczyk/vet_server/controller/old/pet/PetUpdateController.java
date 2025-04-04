//package com.jakubolejarczyk.vet_server.controller.old.pet;
//
//import com.jakubolejarczyk.vet_server.dto.request.dependent.PetRequest;
//import com.jakubolejarczyk.vet_server.dto.response.Response;
//import com.jakubolejarczyk.vet_server.model.dependent.Pet;
//import com.jakubolejarczyk.vet_server.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
//import com.jakubolejarczyk.vet_server.step.check.CheckClientExistStepRunner;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.step.success.SuccessUpdatePetStepRunner;
//import com.jakubolejarczyk.vet_server.step.update.UpdatePetStepRunner;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import jakarta.validation.Valid;
//import lombok.val;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//
//@RestController
//@RequestMapping("/api/v1")
//public class PetUpdateController extends StepRunnerController {
//    private final CheckClientExistStepRunner checkClientExistStep;
//    private final UpdatePetStepRunner updatePetStep;
//    private final SuccessUpdatePetStepRunner successUpdatePetStep;
//
//    public PetUpdateController(
//            ObjectFactory<StepStore> stepStoreObjectFactory,
//            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
//            CheckClientExistStepRunner checkClientExistStep,
//            UpdatePetStepRunner updatePetStep,
//            SuccessUpdatePetStepRunner successUpdatePetStep
//    ) {
//        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
//        this.checkClientExistStep = checkClientExistStep;
//        this.updatePetStep = updatePetStep;
//        this.successUpdatePetStep = successUpdatePetStep;
//    }
//
//    @PostMapping("pet-update")
//    public ResponseEntity<Response<?, ?>> petUpdate(@Valid @RequestBody PetRequest request) {
//        val steps = new ArrayList<StepRunnerModel>();
//        steps.addLast(checkClientExistStep);
//        steps.addLast(updatePetStep);
//        steps.addLast(successUpdatePetStep);
//        String[] dataKeys = {"pet"};
//        String[] metadataKeys = {};
//        initController(dataKeys, metadataKeys);
//        getStepStore().setItem("token", request.getToken());
//        val requestPet = Pet.builder()
//                .id(request.getId())
//                .fullName(request.getFullName())
//                .species(request.getSpecies())
//                .breed(request.getBreed())
//                .dateOfBirth(request.getDateOfBirth())
//                .weightKg(request.getWeightKg())
//                .color(request.getColor())
//                .sterilized(request.getSterilized())
//                .pictureUrl(request.getPictureUrl())
//                .microchipNumber(request.getMicrochipNumber())
//                .medicalNotes(request.getMedicalNotes())
//                .clientId(request.getClientId())
//                .build();
//        getStepStore().setItem("requestPet", requestPet);
//        getStepStore().setItem("clientId", requestPet.getClientId());
//        return runController(steps);
//    }
//}
