package com.jakubolejarczyk.vet_server.controller.dependent.appointment;

import com.jakubolejarczyk.vet_server.dto.data.dependent.AppointmentData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.AppointmentMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.appointment.GetAppointmentsByClinicsStep;
import com.jakubolejarczyk.vet_server.step.get.client.GetClientsByClinicsStep;
import com.jakubolejarczyk.vet_server.step.get.clinic.GetClinicsByEmploymentsStep;
import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentsByAccountStep;
import com.jakubolejarczyk.vet_server.step.get.pet.GetPetsByClientsStep;
import com.jakubolejarczyk.vet_server.step.metadata.AppointmentMetadataStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.appointment.AppointmentReadResponseStep;
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
public class AppointmentReadController extends StepRunnerController<AppointmentData, AppointmentMetadata> {
    private final GetAccountByTokenStep<AppointmentData, AppointmentMetadata> getAccountByTokenStep;
    private final GetEmploymentsByAccountStep<AppointmentData, AppointmentMetadata> getEmploymentsByAccountStep;
    private final GetClinicsByEmploymentsStep<AppointmentData, AppointmentMetadata> getClinicsByEmploymentsStep;
    private final GetClientsByClinicsStep<AppointmentData, AppointmentMetadata> getClientsByClinicsStep;
    private final GetPetsByClientsStep<AppointmentData, AppointmentMetadata> getPetsByClientsStep;
    private final GetAppointmentsByClinicsStep<AppointmentData, AppointmentMetadata> getAppointmentsByClinicsStep;
    private final AppointmentMetadataStep appointmentMetadataStep;
    private final AppointmentReadResponseStep appointmentReadResponseStep;

    public AppointmentReadController(
        ObjectFactory<StepStore<AppointmentData, AppointmentMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<AppointmentData, AppointmentMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<AppointmentData, AppointmentMetadata> getAccountByTokenStep,
        GetEmploymentsByAccountStep<AppointmentData, AppointmentMetadata> getEmploymentsByAccountStep,
        GetClinicsByEmploymentsStep<AppointmentData, AppointmentMetadata> getClinicsByEmploymentsStep,
        GetClientsByClinicsStep<AppointmentData, AppointmentMetadata> getClientsByClinicsStep,
        GetPetsByClientsStep<AppointmentData, AppointmentMetadata> getPetsByClientsStep,
        GetAppointmentsByClinicsStep<AppointmentData, AppointmentMetadata> getAppointmentsByClinicsStep,
        AppointmentMetadataStep appointmentMetadataStep,
        AppointmentReadResponseStep appointmentReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountStep = getEmploymentsByAccountStep;
        this.getClinicsByEmploymentsStep = getClinicsByEmploymentsStep;
        this.getClientsByClinicsStep = getClientsByClinicsStep;
        this.getPetsByClientsStep = getPetsByClientsStep;
        this.getAppointmentsByClinicsStep = getAppointmentsByClinicsStep;
        this.appointmentMetadataStep = appointmentMetadataStep;
        this.appointmentReadResponseStep = appointmentReadResponseStep;
    }

    @PostMapping("appointment-read")
    public ResponseEntity<Response<AppointmentData, AppointmentMetadata>> appointmentRead(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<AppointmentData, AppointmentMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountStep);
        steps.addLast(getClinicsByEmploymentsStep);
        steps.addLast(getClientsByClinicsStep);
        steps.addLast(getPetsByClientsStep);
        steps.addLast(getAppointmentsByClinicsStep);
        steps.addLast(appointmentMetadataStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, appointmentReadResponseStep);
    }
}
