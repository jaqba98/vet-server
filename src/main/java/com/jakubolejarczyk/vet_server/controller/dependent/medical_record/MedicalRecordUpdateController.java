package com.jakubolejarczyk.vet_server.controller.dependent.medical_record;

import com.jakubolejarczyk.vet_server.dto.data.independent.MedicalRecordData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.MedicalRecordMetadata;
import com.jakubolejarczyk.vet_server.dto.request.dependent.MedicalRecordRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.MedicalRecord;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.medical_record.MedicalRecordUpdateResponseStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateMedicalRecordStep;
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
public class MedicalRecordUpdateController extends StepRunnerController<MedicalRecordData, MedicalRecordMetadata> {
    private final GetAccountByTokenStep<MedicalRecordData, MedicalRecordMetadata> getAccountByTokenStep;
    private final UpdateMedicalRecordStep<MedicalRecordData, MedicalRecordMetadata> updateMedicalRecordStep;
    private final MedicalRecordUpdateResponseStep medicalRecordUpdateResponseStep;

    public MedicalRecordUpdateController(
        ObjectFactory<StepStore<MedicalRecordData, MedicalRecordMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<MedicalRecordData, MedicalRecordMetadata> getAccountByTokenStep,
        UpdateMedicalRecordStep<MedicalRecordData, MedicalRecordMetadata> updateMedicalRecordStep,
        MedicalRecordUpdateResponseStep medicalRecordUpdateResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.updateMedicalRecordStep = updateMedicalRecordStep;
        this.medicalRecordUpdateResponseStep = medicalRecordUpdateResponseStep;
    }

    @PostMapping("medical-record-update")
    public ResponseEntity<Response<MedicalRecordData, MedicalRecordMetadata>> medicalRecordUpdate(
        @Valid @RequestBody MedicalRecordRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<MedicalRecordData, MedicalRecordMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(updateMedicalRecordStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        val medicalRecordRequest = MedicalRecord.builder()
            .id(request.getId())
            .diagnosis(request.getDiagnosis())
            .treatment(request.getTreatment())
            .procedures(request.getProcedures())
            .nextAppointment(request.getNextAppointment())
            .status(request.getStatus())
            .notes(request.getNotes())
            .appointmentId(request.getAppointmentId())
            .build();
        getStepStore().setItem("medicalRecordRequest", medicalRecordRequest);
        return runController(steps, medicalRecordUpdateResponseStep);
    }
}
