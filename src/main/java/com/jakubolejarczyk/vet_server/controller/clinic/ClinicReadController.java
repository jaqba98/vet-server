package com.jakubolejarczyk.vet_server.controller.clinic;

import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.GetClinicByIdsStep;
import com.jakubolejarczyk.vet_server.step.get.GetClinicIdsForAccountStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
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
public class ClinicReadController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final GetClinicIdsForAccountStep getClinicIdsForAccountStep;
    private final GetClinicByIdsStep getClinicByIdsStep;

    public ClinicReadController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            GetClinicIdsForAccountStep getClinicIdsForAccountStep,
            GetClinicByIdsStep getClinicByIdsStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getClinicIdsForAccountStep = getClinicIdsForAccountStep;
        this.getClinicByIdsStep = getClinicByIdsStep;
    }

    @PostMapping("clinic-read")
    public ResponseEntity<Response<?, ?>> clinicRead(@RequestBody TokenRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getClinicIdsForAccountStep);
        steps.addLast(getClinicByIdsStep);
        String[] dataKeys = {"clinics"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        return runController(steps);
    }
}
