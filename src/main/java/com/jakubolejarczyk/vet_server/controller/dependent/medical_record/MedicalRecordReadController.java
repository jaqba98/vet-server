package com.jakubolejarczyk.vet_server.controller.dependent.medical_record;

import com.jakubolejarczyk.vet_server.dto.data.dependent.MedicalRecordData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.MedicalRecordMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.appointment.GetAppointmentsByClinicsStep;
import com.jakubolejarczyk.vet_server.step.get.clinic.GetClinicsByEmploymentsStep;
import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentsByAccountStep;
import com.jakubolejarczyk.vet_server.step.get.medical_record.GetMedicalRecordsByAppointmentsStep;
import com.jakubolejarczyk.vet_server.step.metadata.MedicalRecordMetadataStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.medical_record.MedicalRecordReadResponseStep;
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
public class MedicalRecordReadController extends StepRunnerController<MedicalRecordData, MedicalRecordMetadata> {
    private final GetAccountByTokenStep<MedicalRecordData, MedicalRecordMetadata> getAccountByTokenStep;
    private final GetEmploymentsByAccountStep<MedicalRecordData, MedicalRecordMetadata> getEmploymentsByAccountStep;
    private final GetClinicsByEmploymentsStep<MedicalRecordData, MedicalRecordMetadata> getClinicsByEmploymentsStep;
    private final GetAppointmentsByClinicsStep<MedicalRecordData, MedicalRecordMetadata> getAppointmentsByClinicsStep;
    private final GetMedicalRecordsByAppointmentsStep<MedicalRecordData, MedicalRecordMetadata> getMedicalRecordsByAppointmentsStep;
    private final MedicalRecordMetadataStep medicalRecordMetadataStep;
    private final MedicalRecordReadResponseStep medicalRecordReadResponseStep;

    public MedicalRecordReadController(
        ObjectFactory<StepStore<MedicalRecordData, MedicalRecordMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<MedicalRecordData, MedicalRecordMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<MedicalRecordData, MedicalRecordMetadata> getAccountByTokenStep,
        GetEmploymentsByAccountStep<MedicalRecordData, MedicalRecordMetadata> getEmploymentsByAccountStep,
        GetClinicsByEmploymentsStep<MedicalRecordData, MedicalRecordMetadata> getClinicsByEmploymentsStep,
        GetAppointmentsByClinicsStep<MedicalRecordData, MedicalRecordMetadata> getAppointmentsByClinicsStep,
        GetMedicalRecordsByAppointmentsStep<MedicalRecordData, MedicalRecordMetadata> getMedicalRecordsByAppointmentsStep,
        MedicalRecordMetadataStep medicalRecordMetadataStep,
        MedicalRecordReadResponseStep medicalRecordReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountStep = getEmploymentsByAccountStep;
        this.getClinicsByEmploymentsStep = getClinicsByEmploymentsStep;
        this.getAppointmentsByClinicsStep = getAppointmentsByClinicsStep;
        this.getMedicalRecordsByAppointmentsStep = getMedicalRecordsByAppointmentsStep;
        this.medicalRecordMetadataStep = medicalRecordMetadataStep;
        this.medicalRecordReadResponseStep = medicalRecordReadResponseStep;
    }

    @PostMapping("medical-record-read")
    public ResponseEntity<Response<MedicalRecordData, MedicalRecordMetadata>> medicalRecordRead(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<MedicalRecordData, MedicalRecordMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountStep);
        steps.addLast(getClinicsByEmploymentsStep);
        steps.addLast(getAppointmentsByClinicsStep);
        steps.addLast(getMedicalRecordsByAppointmentsStep);
        steps.addLast(medicalRecordMetadataStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, medicalRecordReadResponseStep);
    }
}
