package com.jakubolejarczyk.vet_server.controller.dependent.appointment;

import com.jakubolejarczyk.vet_server.dto.data.dependent.AppointmentData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.AppointmentMetadata;
import com.jakubolejarczyk.vet_server.dto.request.dependent.AppointmentRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStep;
import com.jakubolejarczyk.vet_server.step.create.CreateAppointmentStep;
import com.jakubolejarczyk.vet_server.step.create.CreateInvoiceStep;
import com.jakubolejarczyk.vet_server.step.create.CreateMedicalRecordStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.appointment.AppointmentCreateResponseStep;
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
public class AppointmentCreateController extends StepRunnerController<AppointmentData, AppointmentMetadata> {
    private final GetAccountByTokenStep<AppointmentData, AppointmentMetadata> getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStep<AppointmentData, AppointmentMetadata> checkAccountPermissionToClinicStep;
    private final CreateAppointmentStep<AppointmentData, AppointmentMetadata> createAppointmentStep;
    private final CreateInvoiceStep<AppointmentData, AppointmentMetadata> createInvoiceStep;
    private final CreateMedicalRecordStep<AppointmentData, AppointmentMetadata> createMedicalRecordStep;
    private final AppointmentCreateResponseStep appointmentCreateResponseStep;

    public AppointmentCreateController(
        ObjectFactory<StepStore<AppointmentData, AppointmentMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<AppointmentData, AppointmentMetadata> getAccountByTokenStep,
        CheckAccountPermissionToClinicStep<AppointmentData, AppointmentMetadata> checkAccountPermissionToClinicStep,
        CreateAppointmentStep<AppointmentData, AppointmentMetadata> createAppointmentStep,
        CreateInvoiceStep<AppointmentData, AppointmentMetadata> createInvoiceStep,
        CreateMedicalRecordStep<AppointmentData, AppointmentMetadata> createMedicalRecordStep,
        AppointmentCreateResponseStep appointmentCreateResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.createAppointmentStep = createAppointmentStep;
        this.createInvoiceStep = createInvoiceStep;
        this.createMedicalRecordStep = createMedicalRecordStep;
        this.appointmentCreateResponseStep = appointmentCreateResponseStep;
    }

    @PostMapping("appointment-create")
    public ResponseEntity<Response<AppointmentData, AppointmentMetadata>> appointmentCreate(
        @Valid @RequestBody AppointmentRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<AppointmentData, AppointmentMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(createAppointmentStep);
        steps.addLast(createInvoiceStep);
        steps.addLast(createMedicalRecordStep);
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
        return runController(steps, appointmentCreateResponseStep);
    }
}
