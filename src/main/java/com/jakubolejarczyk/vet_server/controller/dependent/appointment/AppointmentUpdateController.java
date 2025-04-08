package com.jakubolejarczyk.vet_server.controller.dependent.appointment;

import com.jakubolejarczyk.vet_server.dto.data.dependent.AppointmentData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.AppointmentMetadata;
import com.jakubolejarczyk.vet_server.dto.request.dependent.AppointmentRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.appointment.AppointmentUpdateResponseStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateAppointmentStep;
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
public class AppointmentUpdateController extends StepRunnerController<AppointmentData, AppointmentMetadata> {
    private final GetAccountByTokenStep<AppointmentData, AppointmentMetadata> getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStep<AppointmentData, AppointmentMetadata> checkAccountPermissionToClinicStep;
    private final UpdateAppointmentStep<AppointmentData, AppointmentMetadata> updateAppointmentStep;
    private final AppointmentUpdateResponseStep appointmentUpdateResponseStep;

    public AppointmentUpdateController(
        ObjectFactory<StepStore<AppointmentData, AppointmentMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<AppointmentData, AppointmentMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<AppointmentData, AppointmentMetadata> getAccountByTokenStep,
        CheckAccountPermissionToClinicStep<AppointmentData, AppointmentMetadata> checkAccountPermissionToClinicStep,
        UpdateAppointmentStep<AppointmentData, AppointmentMetadata> updateAppointmentStep,
        AppointmentUpdateResponseStep appointmentUpdateResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.updateAppointmentStep = updateAppointmentStep;
        this.appointmentUpdateResponseStep = appointmentUpdateResponseStep;
    }

    @PostMapping("appointment-update")
    public ResponseEntity<Response<AppointmentData, AppointmentMetadata>> appointmentUpdate(
        @Valid @RequestBody AppointmentRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<AppointmentData, AppointmentMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(updateAppointmentStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        val appointmentRequest = Appointment.builder()
            .id(request.getId())
            .fullName(request.getFullName())
            .dateAndHour(request.getDateAndHour())
            .type(request.getType())
            .status(request.getStatus())
            .reason(request.getReason())
            .notes(request.getNotes())
            .clinicId(request.getClinicId())
            .vetId(request.getVetId())
            .petId(request.getPetId())
            .build();
        getStepStore().setItem("appointmentRequest", appointmentRequest);
        getStepStore().setItem("clinicId", appointmentRequest.getClinicId());
        return runController(steps, appointmentUpdateResponseStep);
    }
}
