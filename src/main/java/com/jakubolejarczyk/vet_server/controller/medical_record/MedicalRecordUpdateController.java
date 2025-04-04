package com.jakubolejarczyk.vet_server.controller.medical_record;

import com.jakubolejarczyk.vet_server.dto.request.independent.MedicalRecordRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.independent.MedicalRecord;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessUpdateInvoiceStepRunner;
import com.jakubolejarczyk.vet_server.step.update.UpdateMedicalRecordStepRunner;
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
public class MedicalRecordUpdateController extends StepRunnerController {
    private final UpdateMedicalRecordStepRunner updateMedicalRecordStep;
    private final SuccessUpdateInvoiceStepRunner successUpdateInvoiceStep;

    public MedicalRecordUpdateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            UpdateMedicalRecordStepRunner updateMedicalRecordStep,
            SuccessUpdateInvoiceStepRunner successUpdateInvoiceStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.updateMedicalRecordStep = updateMedicalRecordStep;
        this.successUpdateInvoiceStep = successUpdateInvoiceStep;
    }

    @PostMapping("medical-record-update")
    public ResponseEntity<Response<?, ?>> medicalRecordUpdate(@Valid @RequestBody MedicalRecordRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(updateMedicalRecordStep);
        steps.addLast(successUpdateInvoiceStep);
        String[] dataKeys = {"medicalRecord"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val requestMedicalRecord = MedicalRecord.builder()
                .id(request.getId())
                .diagnosis(request.getDiagnosis())
                .treatment(request.getTreatment())
                .procedures(request.getProcedures())
                .nextAppointment(request.getNextAppointment())
                .status(request.getStatus())
                .notes(request.getNotes())
                .build();
        getStepStore().setItem("requestMedicalRecord", requestMedicalRecord);
        return runController(steps);
    }
}
