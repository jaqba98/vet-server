package com.jakubolejarczyk.vet_server.controller.dependent.medication;

import com.jakubolejarczyk.vet_server.dto.data.dependent.MedicationData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.MedicationMetadata;
import com.jakubolejarczyk.vet_server.dto.request.logic.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.delete.DeleteMedicationsStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.medication.MedicationDeleteResponseStep;
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
public class MedicationDeleteController extends StepRunnerController<MedicationData, MedicationMetadata> {
    private final GetAccountByTokenStep<MedicationData, MedicationMetadata> getAccountByTokenStep;
    private final DeleteMedicationsStep<MedicationData, MedicationMetadata> deleteMedicationsStep;
    private final MedicationDeleteResponseStep medicationDeleteResponseStep;

    public MedicationDeleteController(
        ObjectFactory<StepStore<MedicationData, MedicationMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<MedicationData, MedicationMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<MedicationData, MedicationMetadata> getAccountByTokenStep,
        DeleteMedicationsStep<MedicationData, MedicationMetadata> deleteMedicationsStep,
        MedicationDeleteResponseStep medicationDeleteResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.deleteMedicationsStep = deleteMedicationsStep;
        this.medicationDeleteResponseStep = medicationDeleteResponseStep;
    }

    @PostMapping("medication-delete")
    public ResponseEntity<Response<MedicationData, MedicationMetadata>> medicationDelete(
        @Valid @RequestBody DeleteRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<MedicationData, MedicationMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(deleteMedicationsStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("medicationIds", request.getIds());
        return runController(steps, medicationDeleteResponseStep);
    }
}
