package com.jakubolejarczyk.vet_server.controller.appointment;

import com.jakubolejarczyk.vet_server.dto.request.appointment.AppointmentRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStep;
import com.jakubolejarczyk.vet_server.step.create.CreateAppointmentStep;
import com.jakubolejarczyk.vet_server.step.create.CreateInvoiceStep;
import com.jakubolejarczyk.vet_server.step.create.CreateMedicalRecordStep;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessCreateAppointmentStep;
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
public class AppointmentCreateController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStep checkAccountPermissionToClinicStep;
    private final CreateInvoiceStep createInvoiceStep;
    private final CreateMedicalRecordStep createMedicalRecordStep;
    private final CreateAppointmentStep createAppointmentStep;
    private final SuccessCreateAppointmentStep successCreateAppointmentStep;

    public AppointmentCreateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            CheckAccountPermissionToClinicStep checkAccountPermissionToClinicStep,
            CreateInvoiceStep createInvoiceStep,
            CreateMedicalRecordStep createMedicalRecordStep,
            CreateAppointmentStep createAppointmentStep,
            SuccessCreateAppointmentStep successCreateAppointmentStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.createInvoiceStep = createInvoiceStep;
        this.createMedicalRecordStep = createMedicalRecordStep;
        this.createAppointmentStep = createAppointmentStep;
        this.successCreateAppointmentStep = successCreateAppointmentStep;
    }

    @PostMapping("appointment-create")
    public ResponseEntity<Response<?, ?>> appointmentCreate(@Valid @RequestBody AppointmentRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(createInvoiceStep);
        steps.addLast(createMedicalRecordStep);
        steps.addLast(createAppointmentStep);
        steps.addLast(successCreateAppointmentStep);
        String[] dataKeys = {};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val requestAppointment = Appointment.builder()
                .id(request.getId())
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
