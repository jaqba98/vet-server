package com.jakubolejarczyk.vet_server.controller.dependent.opening_hour;

import com.jakubolejarczyk.vet_server.dto.data.independent.OpeningHourData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.OpeningHourMetadata;
import com.jakubolejarczyk.vet_server.dto.request.dependent.OpeningHourRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.OpeningHour;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentByAccountAndClinicIdAndIsOwnerStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.opening_hour.OpeningHourUpdateResponseStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateOpeningHourByEmploymentStep;
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
public class OpeningHourUpdateController extends StepRunnerController<OpeningHourData, OpeningHourMetadata> {
    private final GetAccountByTokenStep<OpeningHourData, OpeningHourMetadata> getAccountByTokenStep;
    private final GetEmploymentByAccountAndClinicIdAndIsOwnerStep<OpeningHourData, OpeningHourMetadata> getEmploymentByAccountAndClinicIdAndIsOwnerStep;
    private final UpdateOpeningHourByEmploymentStep<OpeningHourData, OpeningHourMetadata> updateOpeningHourByEmploymentStep;
    private final OpeningHourUpdateResponseStep openingHourUpdateResponseStep;

    public OpeningHourUpdateController(
        ObjectFactory<StepStore<OpeningHourData, OpeningHourMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<OpeningHourData, OpeningHourMetadata> getAccountByTokenStep,
        GetEmploymentByAccountAndClinicIdAndIsOwnerStep<OpeningHourData, OpeningHourMetadata> getEmploymentByAccountAndClinicIdAndIsOwnerStep,
        UpdateOpeningHourByEmploymentStep<OpeningHourData, OpeningHourMetadata> updateOpeningHourByEmploymentStep,
        OpeningHourUpdateResponseStep openingHourUpdateResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentByAccountAndClinicIdAndIsOwnerStep = getEmploymentByAccountAndClinicIdAndIsOwnerStep;
        this.updateOpeningHourByEmploymentStep = updateOpeningHourByEmploymentStep;
        this.openingHourUpdateResponseStep = openingHourUpdateResponseStep;
    }

    @PostMapping("opening-hour-update")
    public ResponseEntity<Response<OpeningHourData, OpeningHourMetadata>> openingHourUpdate(
        @Valid @RequestBody OpeningHourRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<OpeningHourData, OpeningHourMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentByAccountAndClinicIdAndIsOwnerStep);
        steps.addLast(updateOpeningHourByEmploymentStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        val openingHourRequest = OpeningHour.builder()
            .id(request.getId())
            .mondayFrom(request.getMondayFrom())
            .mondayTo(request.getMondayTo())
            .tuesdayFrom(request.getTuesdayFrom())
            .tuesdayTo(request.getTuesdayTo())
            .wednesdayFrom(request.getWednesdayFrom())
            .wednesdayTo(request.getWednesdayTo())
            .thursdayFrom(request.getThursdayFrom())
            .thursdayTo(request.getThursdayTo())
            .fridayFrom(request.getFridayFrom())
            .fridayTo(request.getFridayTo())
            .saturdayFrom(request.getSaturdayFrom())
            .saturdayTo(request.getSaturdayTo())
            .sundayFrom(request.getSundayFrom())
            .sundayTo(request.getSundayTo())
            .clinicId(request.getClinicId())
            .build();
        getStepStore().setItem("openingHourRequest", openingHourRequest);
        getStepStore().setItem("clinicId", request.getClinicId());
        return runController(steps, openingHourUpdateResponseStep);
    }
}
