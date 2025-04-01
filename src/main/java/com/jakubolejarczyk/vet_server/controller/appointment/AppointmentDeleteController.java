package com.jakubolejarczyk.vet_server.controller.appointment;

import com.jakubolejarczyk.vet_server.dto.request.appointment.AppointmentRequest;
import com.jakubolejarczyk.vet_server.dto.request.common.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStep;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessDeleteAppointmentStep;
import com.jakubolejarczyk.vet_server.step.success.SuccessUpdateAppointmentStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateAppointmentIsArchivedStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateAppointmentStep;
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
public class AppointmentDeleteController extends BaseController {
    private final UpdateAppointmentIsArchivedStep updateAppointmentIsArchivedStep;
    private final SuccessDeleteAppointmentStep successDeleteAppointmentStep;

    public AppointmentDeleteController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            UpdateAppointmentIsArchivedStep updateAppointmentIsArchivedStep,
            SuccessDeleteAppointmentStep successDeleteAppointmentStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.updateAppointmentIsArchivedStep = updateAppointmentIsArchivedStep;
        this.successDeleteAppointmentStep = successDeleteAppointmentStep;
    }

    @PostMapping("appointment-delete")
    public ResponseEntity<Response<?, ?>> appointmentDelete(@Valid @RequestBody DeleteRequest request) {
        val steps = new ArrayList<StepModel>();
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
