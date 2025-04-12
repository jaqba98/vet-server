package com.jakubolejarczyk.vet_server.controller.independent.clinic;

import com.jakubolejarczyk.vet_server.dto.data.independent.ClinicData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.ClinicMetadata;
import com.jakubolejarczyk.vet_server.dto.request.logic.DeleteRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.delete.DeleteClinicsStep;
import com.jakubolejarczyk.vet_server.step.delete.DeleteEmploymentsByClinicsStep;
import com.jakubolejarczyk.vet_server.step.delete.DeleteOpeningHoursByClinicsStep;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.clinic.GetClinicsByEmploymentsStep;
import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentsByAccountAndClinicsAndIsOwnerStep;
import com.jakubolejarczyk.vet_server.step.response.independent.clinic.ClinicDeleteResponseStep;
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
public class ClinicDeleteController extends StepRunnerController<ClinicData, ClinicMetadata> {
    private final GetAccountByTokenStep<ClinicData, ClinicMetadata> getAccountByTokenStep;
    private final GetEmploymentsByAccountAndClinicsAndIsOwnerStep<ClinicData, ClinicMetadata> getEmploymentsByAccountAndClinicsAndIsOwnerStep;
    private final GetClinicsByEmploymentsStep<ClinicData, ClinicMetadata> getClinicsByEmploymentsStep;
    private final DeleteOpeningHoursByClinicsStep<ClinicData, ClinicMetadata> deleteOpeningHoursByClinicsStep;
    private final DeleteEmploymentsByClinicsStep<ClinicData, ClinicMetadata> deleteEmploymentsByClinicsStep;
    private final DeleteClinicsStep<ClinicData, ClinicMetadata> deleteClinicsStep;
    private final ClinicDeleteResponseStep clinicDeleteResponseStep;

    public ClinicDeleteController(
        ObjectFactory<StepStore<ClinicData, ClinicMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<ClinicData, ClinicMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<ClinicData, ClinicMetadata> getAccountByTokenStep,
        GetEmploymentsByAccountAndClinicsAndIsOwnerStep<ClinicData, ClinicMetadata> getEmploymentsByAccountAndClinicsAndIsOwnerStep,
        GetClinicsByEmploymentsStep<ClinicData, ClinicMetadata> getClinicsByEmploymentsStep,
        DeleteOpeningHoursByClinicsStep<ClinicData, ClinicMetadata> deleteOpeningHoursByClinicsStep,
        DeleteEmploymentsByClinicsStep<ClinicData, ClinicMetadata> deleteEmploymentsByClinicsStep,
        DeleteClinicsStep<ClinicData, ClinicMetadata> deleteClinicsStep,
        ClinicDeleteResponseStep clinicDeleteResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountAndClinicsAndIsOwnerStep = getEmploymentsByAccountAndClinicsAndIsOwnerStep;
        this.getClinicsByEmploymentsStep = getClinicsByEmploymentsStep;
        this.deleteOpeningHoursByClinicsStep = deleteOpeningHoursByClinicsStep;
        this.deleteEmploymentsByClinicsStep = deleteEmploymentsByClinicsStep;
        this.deleteClinicsStep = deleteClinicsStep;
        this.clinicDeleteResponseStep = clinicDeleteResponseStep;
    }

    @PostMapping("clinic-delete")
    public ResponseEntity<Response<ClinicData, ClinicMetadata>> clinicDelete(
        @Valid @RequestBody DeleteRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<ClinicData, ClinicMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountAndClinicsAndIsOwnerStep);
        steps.addLast(getClinicsByEmploymentsStep);
        steps.addLast(deleteOpeningHoursByClinicsStep);
        steps.addLast(deleteEmploymentsByClinicsStep);
        steps.addLast(deleteClinicsStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        getStepStore().setItem("clinicIds", request.getIds());
        return runController(steps, clinicDeleteResponseStep);
    }
}
