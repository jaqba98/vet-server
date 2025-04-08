package com.jakubolejarczyk.vet_server.controller.dependent.appointment;

import com.jakubolejarczyk.vet_server.dto.data.dependent.AppointmentData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.AppointmentMetadata;
import com.jakubolejarczyk.vet_server.dto.request.logic.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.delete.DeleteAppointmentStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.appointment.AppointmentDeleteResponseStep;
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
public class AppointmentDeleteController extends StepRunnerController<AppointmentData, AppointmentMetadata> {
    private final GetAccountByTokenStep<AppointmentData, AppointmentMetadata> getAccountByTokenStep;
    private final DeleteAppointmentStep<AppointmentData, AppointmentMetadata> deleteAppointmentStep;
    private final AppointmentDeleteResponseStep appointmentDeleteResponseStep;

    public AppointmentDeleteController(
        ObjectFactory<StepStore<AppointmentData, AppointmentMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<AppointmentData, AppointmentMetadata> getAccountByTokenStep,
        DeleteAppointmentStep<AppointmentData, AppointmentMetadata> deleteAppointmentStep,
        AppointmentDeleteResponseStep appointmentDeleteResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.deleteAppointmentStep = deleteAppointmentStep;
        this.appointmentDeleteResponseStep = appointmentDeleteResponseStep;
    }

    @PostMapping("appointment-delete")
    public ResponseEntity<Response<AppointmentData, AppointmentMetadata>> appointmentDelete(
        @Valid @RequestBody DeleteRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<AppointmentData, AppointmentMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(deleteAppointmentStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("appointmentsIds", request.getIds());
        return runController(steps, appointmentDeleteResponseStep);
    }
}
