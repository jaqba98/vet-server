package com.jakubolejarczyk.vet_server.controller.medical_record;

import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.*;
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
public class MedicalRecordReadController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final GetClinicIdsForAccountStep getClinicIdsForAccountStep;
    private final GetAppointmentsByClinicIdsStep getAppointmentsByClinicIdsStep;
    private final GetMedicalRecordsByAppointmentsStep getMedicalRecordsByAppointmentsStep;

    public MedicalRecordReadController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            GetClinicIdsForAccountStep getClinicIdsForAccountStep,
            GetAppointmentsByClinicIdsStep getAppointmentsByClinicIdsStep,
            GetMedicalRecordsByAppointmentsStep getMedicalRecordsByAppointmentsStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getClinicIdsForAccountStep = getClinicIdsForAccountStep;
        this.getAppointmentsByClinicIdsStep = getAppointmentsByClinicIdsStep;
        this.getMedicalRecordsByAppointmentsStep = getMedicalRecordsByAppointmentsStep;
    }

    @PostMapping("medical-record-read")
    public ResponseEntity<Response<?, ?>> medicalRecordRead(@Valid @RequestBody TokenRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getClinicIdsForAccountStep);
        steps.addLast(getAppointmentsByClinicIdsStep);
        steps.addLast(getMedicalRecordsByAppointmentsStep);
        String[] dataKeys = {"medicalRecords"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        return runController(steps);
    }
}
