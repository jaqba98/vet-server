package com.jakubolejarczyk.vet_server.controller.pet;

import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.GetClientsByEmploymentStep;
import com.jakubolejarczyk.vet_server.step.get.GetEmploymentsByAccountIdAndIsOwnerStep;
import com.jakubolejarczyk.vet_server.step.get.GetPetsByClientsStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
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
public class PetReadController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final GetEmploymentsByAccountIdAndIsOwnerStep getEmploymentsByAccountIdAndIsOwnerStep;
    private final GetClientsByEmploymentStep getClientsByEmploymentStep;
    private final GetPetsByClientsStep getPetsByClientsStep;

    public PetReadController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            GetEmploymentsByAccountIdAndIsOwnerStep getEmploymentsByAccountIdAndIsOwnerStep,
            GetClientsByEmploymentStep getClientsByEmploymentStep,
            GetPetsByClientsStep getPetsByClientsStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountIdAndIsOwnerStep = getEmploymentsByAccountIdAndIsOwnerStep;
        this.getClientsByEmploymentStep = getClientsByEmploymentStep;
        this.getPetsByClientsStep = getPetsByClientsStep;
    }

    @PostMapping("pet-read")
    public ResponseEntity<Response<?, ?>> petRead(@Valid @RequestBody TokenRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountIdAndIsOwnerStep);
        steps.addLast(getClientsByEmploymentStep);
        steps.addLast(getPetsByClientsStep);
        String[] dataKeys = {"pets"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        return runController(steps);
    }
}
