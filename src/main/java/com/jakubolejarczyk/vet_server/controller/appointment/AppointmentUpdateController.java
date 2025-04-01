package com.jakubolejarczyk.vet_server.controller.appointment;

import com.jakubolejarczyk.vet_server.dto.request.appointment.AppointmentRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStep;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessUpdateAppointmentStep;
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
public class AppointmentUpdateController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStep checkAccountPermissionToClinicStep;
    private final UpdateAppointmentStep updateAppointmentStep;
    private final SuccessUpdateAppointmentStep successUpdateAppointmentStep;

    public AppointmentUpdateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            CheckAccountPermissionToClinicStep checkAccountPermissionToClinicStep,
            UpdateAppointmentStep updateAppointmentStep,
            SuccessUpdateAppointmentStep successUpdateAppointmentStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.updateAppointmentStep = updateAppointmentStep;
        this.successUpdateAppointmentStep = successUpdateAppointmentStep;
    }

    @PostMapping("appointment-update")
    public ResponseEntity<Response<?, ?>> appointmentUpdate(@Valid @RequestBody AppointmentRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(updateAppointmentStep);
        steps.addLast(successUpdateAppointmentStep);
        String[] dataKeys = {"appointment"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val requestAppointment = Appointment.builder()
                .id(request.getId())
                .isArchived(request.getIsArchived())
                .dateAndHour(request.getDateAndHour())
                .type(request.getType())
                .status(request.getStatus())
                .reason(request.getReason())
                .notes(request.getNotes())
                .clinicId(request.getClinicId())
                .vetId(request.getVetId())
                .petId(request.getPetId())
                .invoiceId(request.getInvoiceId())
                .medicalRecordId(request.getMedicalRecordId())
                .build();
        getStepStore().setItem("requestAppointment", requestAppointment);
        getStepStore().setItem("clinicId", requestAppointment.getClinicId());
        return runController(steps);
    }
}
