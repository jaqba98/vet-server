package com.jakubolejarczyk.vet_server.controller.clinic_opening_hours;

import com.jakubolejarczyk.vet_server.dto.request.opening_hours.OpeningHoursRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessUpdateOpeningHoursStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateOpeningHoursStep;
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
public class ClinicOpeningHoursUpdateController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final UpdateOpeningHoursStep updateOpeningHoursStep;
    private final SuccessUpdateOpeningHoursStep successUpdateOpeningHoursStep;

    public ClinicOpeningHoursUpdateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            UpdateOpeningHoursStep updateOpeningHoursStep,
            SuccessUpdateOpeningHoursStep successUpdateOpeningHoursStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.updateOpeningHoursStep = updateOpeningHoursStep;
        this.successUpdateOpeningHoursStep = successUpdateOpeningHoursStep;
    }

    @PostMapping("clinic-opening-hours-update")
    public ResponseEntity<Response<?, ?>> clinicOpeningHoursUpdate(@Valid @RequestBody OpeningHoursRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(updateOpeningHoursStep);
        steps.addLast(successUpdateOpeningHoursStep);
        String[] dataKeys = {"openingHours"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val requestOpeningHours = OpeningHours.builder()
                .id(request.getId())
                .isArchived(request.getIsArchived())
                .mondayFrom(request.getMondayFrom())
                .mondayTo(request.getMondayTo())
                .tuesdayFrom(request.getTuesdayFrom())
                .thursdayTo(request.getTuesdayTo())
                .wednesdayFrom(request.getWednesdayFrom())
                .wednesdayTo(request.getWednesdayTo())
                .thursdayFrom(request.getThursdayFrom())
                .thursdayTo(request.getTuesdayTo())
                .fridayFrom(request.getFridayFrom())
                .fridayTo(request.getFridayTo())
                .saturdayFrom(request.getSaturdayFrom())
                .saturdayTo(request.getSaturdayTo())
                .sundayFrom(request.getSundayFrom())
                .sundayTo(request.getSundayTo())
                .build();
        getStepStore().setItem("requestOpeningHours", requestOpeningHours);
        return runController(steps);
    }
}
