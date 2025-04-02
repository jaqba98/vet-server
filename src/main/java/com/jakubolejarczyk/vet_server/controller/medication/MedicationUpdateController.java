package com.jakubolejarczyk.vet_server.controller.medication;

import com.jakubolejarczyk.vet_server.dto.request.medication.MedicationRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Medication;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStep;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessUpdateMedicationStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateMedicationStep;
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
public class MedicationUpdateController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final CheckAccountPermissionToClinicStep checkAccountPermissionToClinicStep;
    private final UpdateMedicationStep updateMedicationStep;
    private final SuccessUpdateMedicationStep successUpdateMedicationStep;

    public MedicationUpdateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            CheckAccountPermissionToClinicStep checkAccountPermissionToClinicStep,
            UpdateMedicationStep updateMedicationStep,
            SuccessUpdateMedicationStep successUpdateMedicationStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.checkAccountPermissionToClinicStep = checkAccountPermissionToClinicStep;
        this.updateMedicationStep = updateMedicationStep;
        this.successUpdateMedicationStep = successUpdateMedicationStep;
    }

    @PostMapping("medication-update")
    public ResponseEntity<Response<?, ?>> medicationUpdate(@Valid @RequestBody MedicationRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(checkAccountPermissionToClinicStep);
        steps.addLast(updateMedicationStep);
        steps.addLast(successUpdateMedicationStep);
        String[] dataKeys = {"medication"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val requestMedication = Medication.builder()
                .id(request.getId())
                .isArchived(false)
                .entityName(request.getEntityName())
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
