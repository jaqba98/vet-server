package com.jakubolejarczyk.vet_server.controller.appointment;

import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.GetAppointmentsByClinicIdsStep;
import com.jakubolejarczyk.vet_server.step.get.GetClinicIdsForAccountStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
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
public class AppointmentReadController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final GetClinicIdsForAccountStep getClinicIdsForAccountStep;
    private final GetAppointmentsByClinicIdsStep getAppointmentsByClinicIdsStep;

    public AppointmentReadController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            GetClinicIdsForAccountStep getClinicIdsForAccountStep,
            GetAppointmentsByClinicIdsStep getAppointmentsByClinicIdsStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getClinicIdsForAccountStep = getClinicIdsForAccountStep;
        this.getAppointmentsByClinicIdsStep = getAppointmentsByClinicIdsStep;
    }

    @PostMapping("appointment-read")
    public ResponseEntity<Response<?, ?>> appointmentRead(@Valid @RequestBody TokenRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getClinicIdsForAccountStep);
        steps.addLast(getAppointmentsByClinicIdsStep);
        String[] dataKeys = {"appointments"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        return runController(steps);
    }
}
