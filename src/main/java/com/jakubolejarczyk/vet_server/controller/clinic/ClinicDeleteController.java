package com.jakubolejarczyk.vet_server.controller.clinic;

import com.jakubolejarczyk.vet_server.dto.request.common.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.GetEmploymentsByAccountIdAndClinicIdsInAndIsOwnerStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.update.UpdateClinicsIsArchivedStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateEmploymentsIsArchivedStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateOpeningHoursIsArchivedStep;
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
public class ClinicDeleteController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final GetEmploymentsByAccountIdAndClinicIdsInAndIsOwnerStep getEmploymentsByAccountIdAndClinicIdsInAndIsOwnerStep;
    private final UpdateEmploymentsIsArchivedStep updateEmploymentsIsArchivedStep;
    private final UpdateClinicsIsArchivedStep updateClinicsIsArchivedStep;
    private final UpdateOpeningHoursIsArchivedStep updateOpeningHoursIsArchivedStep;

    public ClinicDeleteController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            GetEmploymentsByAccountIdAndClinicIdsInAndIsOwnerStep getEmploymentsByAccountIdAndClinicIdsInAndIsOwnerStep,
            UpdateEmploymentsIsArchivedStep updateEmploymentsIsArchivedStep,
            UpdateClinicsIsArchivedStep updateClinicsIsArchivedStep,
            UpdateOpeningHoursIsArchivedStep updateOpeningHoursIsArchivedStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountIdAndClinicIdsInAndIsOwnerStep = getEmploymentsByAccountIdAndClinicIdsInAndIsOwnerStep;
        this.updateEmploymentsIsArchivedStep = updateEmploymentsIsArchivedStep;
        this.updateClinicsIsArchivedStep = updateClinicsIsArchivedStep;
        this.updateOpeningHoursIsArchivedStep = updateOpeningHoursIsArchivedStep;
    }

    @PostMapping("clinic-delete")
    public ResponseEntity<Response<?, ?>> clinicDelete(@Valid @RequestBody DeleteRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountIdAndClinicIdsInAndIsOwnerStep);
        steps.addLast(updateEmploymentsIsArchivedStep);
        steps.addLast(updateClinicsIsArchivedStep);
        steps.addLast(updateOpeningHoursIsArchivedStep);
        String[] dataKeys = {};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("ids", request.getIds());
        return runController(steps);
    }
}
