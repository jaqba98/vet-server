package com.jakubolejarczyk.vet_server.controller.appointment;

import com.jakubolejarczyk.vet_server.dto.request.common.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessDeleteAppointmentStepRunner;
import com.jakubolejarczyk.vet_server.step.update.UpdateAppointmentIsArchivedStepRunner;
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
public class AppointmentDeleteController extends StepRunnerController {
    private final UpdateAppointmentIsArchivedStepRunner updateAppointmentIsArchivedStep;
    private final SuccessDeleteAppointmentStepRunner successDeleteAppointmentStep;

    public AppointmentDeleteController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            UpdateAppointmentIsArchivedStepRunner updateAppointmentIsArchivedStep,
            SuccessDeleteAppointmentStepRunner successDeleteAppointmentStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.updateAppointmentIsArchivedStep = updateAppointmentIsArchivedStep;
        this.successDeleteAppointmentStep = successDeleteAppointmentStep;
    }

    @PostMapping("appointment-delete")
    public ResponseEntity<Response<?, ?>> appointmentDelete(@Valid @RequestBody DeleteRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(updateAppointmentIsArchivedStep);
        steps.addLast(successDeleteAppointmentStep);
        String[] dataKeys = {};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("appointmentsIds", request.getIds());
        return runController(steps);
    }
}
