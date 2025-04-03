package com.jakubolejarczyk.vet_server.controller.clinic;

import com.jakubolejarczyk.vet_server.dto.request.common.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step.get.GetEmploymentsByAccountIdAndClinicIdsAndIsOwnerStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessDeleteClinicStepRunner;
import com.jakubolejarczyk.vet_server.step.update.UpdateClinicsIsArchivedStepRunner;
import com.jakubolejarczyk.vet_server.step.update.UpdateEmploymentsIsArchivedStepRunner;
import com.jakubolejarczyk.vet_server.step.update.UpdateOpeningHoursIsArchivedStepRunner;
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
public class ClinicDeleteController extends StepRunnerController {
    private final GetAccountByTokenStepRunner getAccountByTokenStep;
    private final GetEmploymentsByAccountIdAndClinicIdsAndIsOwnerStepRunner getEmploymentsByAccountIdAndClinicIdsAndIsOwnerStep;
    private final UpdateEmploymentsIsArchivedStepRunner updateEmploymentsIsArchivedStep;
    private final UpdateClinicsIsArchivedStepRunner updateClinicsIsArchivedStep;
    private final UpdateOpeningHoursIsArchivedStepRunner updateOpeningHoursIsArchivedStep;
    private final SuccessDeleteClinicStepRunner successDeleteClinicStep;

    public ClinicDeleteController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            GetEmploymentsByAccountIdAndClinicIdsAndIsOwnerStepRunner getEmploymentsByAccountIdAndClinicIdsAndIsOwnerStep,
            UpdateEmploymentsIsArchivedStepRunner updateEmploymentsIsArchivedStep,
            UpdateClinicsIsArchivedStepRunner updateClinicsIsArchivedStep,
            UpdateOpeningHoursIsArchivedStepRunner updateOpeningHoursIsArchivedStep,
            SuccessDeleteClinicStepRunner successDeleteClinicStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountIdAndClinicIdsAndIsOwnerStep = getEmploymentsByAccountIdAndClinicIdsAndIsOwnerStep;
        this.updateEmploymentsIsArchivedStep = updateEmploymentsIsArchivedStep;
        this.updateClinicsIsArchivedStep = updateClinicsIsArchivedStep;
        this.updateOpeningHoursIsArchivedStep = updateOpeningHoursIsArchivedStep;
        this.successDeleteClinicStep = successDeleteClinicStep;
    }

    @PostMapping("clinic-delete")
    public ResponseEntity<Response<?, ?>> clinicDelete(@Valid @RequestBody DeleteRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountIdAndClinicIdsAndIsOwnerStep);
        steps.addLast(updateEmploymentsIsArchivedStep);
        steps.addLast(updateClinicsIsArchivedStep);
        steps.addLast(updateOpeningHoursIsArchivedStep);
        steps.addLast(successDeleteClinicStep);
        String[] dataKeys = {};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("clinicIds", request.getIds());
        return runController(steps);
    }
}
