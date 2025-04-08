package com.jakubolejarczyk.vet_server.controller.dependent.medication;

import com.jakubolejarczyk.vet_server.dto.data.dependent.MedicationData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.MedicationMetadata;
import com.jakubolejarczyk.vet_server.dto.request.dependent.MedicationRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Medication;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.medication.MedicationUpdateResponseStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateMedicationStep;
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
public class MedicationUpdateController extends StepRunnerController<MedicationData, MedicationMetadata> {
    private final GetAccountByTokenStep<MedicationData, MedicationMetadata> getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStep<MedicationData, MedicationMetadata> checkAccountPermissionToClinicStep;
    private final UpdateMedicationStep<MedicationData, MedicationMetadata> updateMedicationStep;
    private final MedicationUpdateResponseStep medicationCreateResponseStep;

    public MedicationUpdateController(
        ObjectFactory<StepStore<MedicationData, MedicationMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<MedicationData, MedicationMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<MedicationData, MedicationMetadata> getAccountByTokenStep,
        CheckAccountPermissionToClinicStep<MedicationData, MedicationMetadata> checkAccountPermissionToClinicStep,
        UpdateMedicationStep<MedicationData, MedicationMetadata> updateMedicationStep,
        MedicationUpdateResponseStep medicationCreateResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.updateMedicationStep = updateMedicationStep;
        this.medicationCreateResponseStep = medicationCreateResponseStep;
    }

    @PostMapping("medication-update")
    public ResponseEntity<Response<MedicationData, MedicationMetadata>> medicationUpdate(
        @Valid @RequestBody MedicationRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<MedicationData, MedicationMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(updateMedicationStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        val medicationRequest = Medication.builder()
            .id(request.getId())
            .fullName(request.getFullName())
            .description(request.getDescription())
            .manufacturer(request.getManufacturer())
            .dose(request.getDose())
            .quantityInStock(request.getQuantityInStock())
            .expirationDate(request.getExpirationDate())
            .price(request.getPrice())
            .isAvailable(request.getIsAvailable())
            .clinicId(request.getClinicId())
            .build();
        getStepStore().setItem("medicationRequest", medicationRequest);
        getStepStore().setItem("clinicId", request.getClinicId());
        return runController(steps, medicationCreateResponseStep);
    }
}
