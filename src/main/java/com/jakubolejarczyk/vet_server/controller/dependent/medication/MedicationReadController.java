package com.jakubolejarczyk.vet_server.controller.dependent.medication;

import com.jakubolejarczyk.vet_server.dto.data.dependent.MedicationData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.MedicationMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.clinic.GetClinicsByEmploymentsStep;
import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentsByAccountStep;
import com.jakubolejarczyk.vet_server.step.get.medication.GetMedicationsByClinicsStep;
import com.jakubolejarczyk.vet_server.step.metadata.MedicationMetadataStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.medication.MedicationReadResponseStep;
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
public class MedicationReadController extends StepRunnerController<MedicationData, MedicationMetadata> {
    private final GetAccountByTokenStep<MedicationData, MedicationMetadata> getAccountByTokenStep;
    private final GetEmploymentsByAccountStep<MedicationData, MedicationMetadata> getEmploymentsByAccountStep;
    private final GetClinicsByEmploymentsStep<MedicationData, MedicationMetadata> getClinicsByEmploymentsStep;
    private final GetMedicationsByClinicsStep<MedicationData, MedicationMetadata> getMedicationsByClinicsStep;
    private final MedicationMetadataStep medicationMetadataStep;
    private final MedicationReadResponseStep medicationReadResponseStep;

    public MedicationReadController(
        ObjectFactory<StepStore<MedicationData, MedicationMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<MedicationData, MedicationMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<MedicationData, MedicationMetadata> getAccountByTokenStep,
        GetEmploymentsByAccountStep<MedicationData, MedicationMetadata> getEmploymentsByAccountStep,
        GetClinicsByEmploymentsStep<MedicationData, MedicationMetadata> getClinicsByEmploymentsStep,
        GetMedicationsByClinicsStep<MedicationData, MedicationMetadata> getMedicationsByClinicsStep,
        MedicationMetadataStep medicationMetadataStep,
        MedicationReadResponseStep medicationReadResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountStep = getEmploymentsByAccountStep;
        this.getClinicsByEmploymentsStep = getClinicsByEmploymentsStep;
        this.getMedicationsByClinicsStep = getMedicationsByClinicsStep;
        this.medicationMetadataStep = medicationMetadataStep;
        this.medicationReadResponseStep = medicationReadResponseStep;
    }

    @PostMapping("medication-read")
    public ResponseEntity<Response<MedicationData, MedicationMetadata>> medicationRead(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<MedicationData, MedicationMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountStep);
        steps.addLast(getClinicsByEmploymentsStep);
        steps.addLast(getMedicationsByClinicsStep);
        steps.addLast(medicationMetadataStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, medicationReadResponseStep);
    }
}
