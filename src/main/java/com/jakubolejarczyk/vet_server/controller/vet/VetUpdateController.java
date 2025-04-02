package com.jakubolejarczyk.vet_server.controller.vet;

import com.jakubolejarczyk.vet_server.dto.request.vet.VetRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessUpdateAccountStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateVetStep;
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
public class VetUpdateController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final UpdateVetStep updateVetStep;
    private final SuccessUpdateAccountStep successUpdateAccountStep;

    public VetUpdateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            UpdateVetStep updateVetStep,
            SuccessUpdateAccountStep successUpdateAccountStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.updateVetStep = updateVetStep;
        this.successUpdateAccountStep = successUpdateAccountStep;
    }

    @PostMapping("vet-update")
    public ResponseEntity<Response<?, ?>> vetUpdate(@Valid @RequestBody VetRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(updateVetStep);
        steps.addLast(successUpdateAccountStep);
        String[] dataKeys = {"vet"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val requestVet = Vet.builder()
                .id(request.getId())
                .isArchived(request.getIsArchived())
                .licenseNumber(request.getLicenseNumber())
                .licenseIssueDate(request.getLicenseIssueDate())
                .licenseExpiryDate(request.getLicenseExpiryDate())
                .specialization(request.getSpecialization())
                .yearsOfExperience(request.getYearsOfExperience())
                .accountId(request.getAccountId())
                .openingHoursId(request.getOpeningHoursId())
                .build();
        getStepStore().setItem("requestVet", requestVet);
        return runController(steps);
    }
}
