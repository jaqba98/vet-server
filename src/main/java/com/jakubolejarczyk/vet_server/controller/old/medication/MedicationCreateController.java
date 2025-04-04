package com.jakubolejarczyk.vet_server.controller.old.medication;

import com.jakubolejarczyk.vet_server.dto.request.dependent.MedicationRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Medication;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStepRunner;
import com.jakubolejarczyk.vet_server.step.create.CreateMedicationStepRunner;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessCreateMedicationStepRunner;
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
public class MedicationCreateController extends StepRunnerController {
    private final GetAccountByTokenStepRunner getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStepRunner checkAccountPermissionToClinicStep;
    private final CreateMedicationStepRunner createMedicationStep;
    private final SuccessCreateMedicationStepRunner successCreateMedicationStep;

    public MedicationCreateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            CheckAccountPermissionToClinicStepRunner checkAccountPermissionToClinicStep,
            CreateMedicationStepRunner createMedicationStep,
            SuccessCreateMedicationStepRunner successCreateMedicationStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.createMedicationStep = createMedicationStep;
        this.successCreateMedicationStep = successCreateMedicationStep;
    }

    @PostMapping("medication-create")
    public ResponseEntity<Response<?, ?>> medicationCreate(@Valid @RequestBody MedicationRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(createMedicationStep);
        steps.addLast(successCreateMedicationStep);
        String[] dataKeys = {};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val requestMedication = Medication.builder()
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
        getStepStore().setItem("requestMedication", requestMedication);
        getStepStore().setItem("clinicId", request.getClinicId());
        return runController(steps);
    }
}
