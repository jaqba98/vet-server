package com.jakubolejarczyk.vet_server.controller.clinic_opening_hours;

import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.request.clinic.ClinicCreateRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.GetClinicIdsForAccountStep;
import com.jakubolejarczyk.vet_server.step.get.GetOpeningHoursForClinicIdsStep;
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
public class ClinicOpeningHoursReadController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final GetClinicIdsForAccountStep getClinicIdsForAccountStep;
    private final GetOpeningHoursForClinicIdsStep getOpeningHoursForClinicIdsStep;

    public ClinicOpeningHoursReadController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            GetClinicIdsForAccountStep getClinicIdsForAccountStep,
            GetOpeningHoursForClinicIdsStep getOpeningHoursForClinicIdsStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getClinicIdsForAccountStep = getClinicIdsForAccountStep;
        this.getOpeningHoursForClinicIdsStep = getOpeningHoursForClinicIdsStep;
    }

    @PostMapping("clinic-opening-hours-read")
    public ResponseEntity<Response<?, ?>> clinicCreate(@Valid @RequestBody TokenRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getClinicIdsForAccountStep);
        steps.addLast(getOpeningHoursForClinicIdsStep);
        String[] dataKeys = {"openingHours"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        return runController(steps);
    }
}


//    @PostMapping("clinic-opening-hours-read")
//    public ResponseEntity<ResponseDataDto<ArrayList<OpeningHours>>> read(@Valid @RequestBody TokenRequestDto requestDto) {
//        // Get Opening Hours Ids For Clinic Ids Step
//        val openingHoursIdsResponse = getOpeningHoursIdsForClinicIdsStep.runStep(responseStep, clinicIds);
//        if (openingHoursIdsResponse.getError()) return responseStep.getStep(false, new ArrayList<>());
//        val openingHoursIds = openingHoursIdsResponse.getData();
//        // Get Opening Hours By Ids Step
//        val openingHoursResponse = getOpeningHoursByIdsStep.runStep(responseStep, openingHoursIds);
//        if (openingHoursResponse.getError()) return responseStep.getStep(false, new ArrayList<>());
//        val openingHours = openingHoursResponse.getData();
//        // Return response
//        responseStep.addMessage("The opening hours have been read successfully!");
//        return responseStep.getStep(true, openingHours);
//    }
