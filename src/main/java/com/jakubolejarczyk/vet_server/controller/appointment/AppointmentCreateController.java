package com.jakubolejarczyk.vet_server.controller.appointment;

import com.jakubolejarczyk.vet_server.dto.request.dependent.AppointmentRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStepRunner;
import com.jakubolejarczyk.vet_server.step.create.CreateAppointmentStepRunner;
import com.jakubolejarczyk.vet_server.step.create.CreateInvoiceStepRunner;
import com.jakubolejarczyk.vet_server.step.create.CreateMedicalRecordStepRunner;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessCreateAppointmentStepRunner;
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
public class AppointmentCreateController extends StepRunnerController {
    private final GetAccountByTokenStepRunner getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStepRunner checkAccountPermissionToClinicStep;
    private final CreateInvoiceStepRunner createInvoiceStep;
    private final CreateMedicalRecordStepRunner createMedicalRecordStep;
    private final CreateAppointmentStepRunner createAppointmentStep;
    private final SuccessCreateAppointmentStepRunner successCreateAppointmentStep;

    public AppointmentCreateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            CheckAccountPermissionToClinicStepRunner checkAccountPermissionToClinicStep,
            CreateInvoiceStepRunner createInvoiceStep,
            CreateMedicalRecordStepRunner createMedicalRecordStep,
            CreateAppointmentStepRunner createAppointmentStep,
            SuccessCreateAppointmentStepRunner successCreateAppointmentStep
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
        val steps = new ArrayList<StepRunnerModel>();
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
