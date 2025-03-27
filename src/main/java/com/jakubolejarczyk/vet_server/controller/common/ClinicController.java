package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.controller.ClinicCreateRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.controller.ClinicUpdateRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.crud.DeleteRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.CreateClinicStep;
import com.jakubolejarczyk.vet_server.service.step.CreateEmploymentStep;
import com.jakubolejarczyk.vet_server.service.step.CreateOpeningHoursStep;
import com.jakubolejarczyk.vet_server.service.step.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.service.step_old.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ClinicController {
    private final ObjectFactory<ResponseStep> responseStep;
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final CreateOpeningHoursStep createOpeningHoursStep;
    private final CreateClinicStep createClinicStep;
    private final ObjectFactory<HandleValidationService> handleValidationService;
    private final CreateEmploymentStep createEmploymentStep;
    private final GetClinicIdsForAccountStep getClinicIdsForAccountStep;
    private final GetClinicByIdsStep getClinicByIdsStep;
    private final GetClinicIdsForOwnerAccountStep getClinicIdsForOwnerAccountStep;
    private final CheckPermissionStep checkPermissionStep;
    private final UpdateClinicStep updateClinicStep;
    private final GetEmploymentByAccountIdAndClinicIdsInAndIsOwnerStep getEmploymentByAccountIdAndClinicIdsInAndIsOwnerStep;
    private final UpdateEmploymentIdsIsArchivedStep updateEmploymentIdsIsArchivedStep;
    private final UpdateClinicIdsIsArchivedStep updateClinicIdsIsArchivedStep;
    private final GetOpeningHoursIdsForClinicIdsStep getOpeningHoursIdsForClinicIdsStep;
    private final UpdateOpeningHoursIdsIsArchivedStep updateOpeningHoursIdsIsArchivedStep;
    private final GetClinicByIdStep getClinicByIdStep;

    @PostMapping("clinic-create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody ClinicCreateRequestDto requestDto) {
        // Init
        val responseStep = this.responseStep.getObject();
        responseStep.getRidOfMessages();
        val isArchived = requestDto.getIsArchived();
        // Get Account By Token Step
        val accountResponse = getAccountByTokenStep.runStep(responseStep, requestDto.getToken());
        if (accountResponse.getError()) return responseStep.getStep(false);
        val account = accountResponse.getData();
        // Create Opening Hours Step
        val openingHoursResponse = createOpeningHoursStep.runStep(responseStep, isArchived);
        if (openingHoursResponse.getError()) return responseStep.getStep(false);
        val openingHours = openingHoursResponse.getData();
        // Create Clinic Step
        val clinicResponse = createClinicStep.runStep(responseStep, requestDto, openingHours.getId());
        if (clinicResponse.getError()) return responseStep.getStep(false);
        val clinic = clinicResponse.getData();
        // Create Employment Step
        val accountId = account.getId();
        val employmentResponse = createEmploymentStep.runStep(responseStep, true, accountId, clinic);
        if (employmentResponse.getError()) return responseStep.getStep(false);
        // Return response
        responseStep.addMessage("The clinic has been created successfully!");
        return responseStep.getStep(true);
    }

    @PostMapping("clinic-read")
    public ResponseEntity<ResponseDataDto<ArrayList<Clinic>>> read(@Valid @RequestBody TokenRequestDto requestDto) {
        // Init
        val responseStep = this.responseStep.getObject();
        responseStep.getRidOfMessages();
        // Get Account By Token Step
        val accountResponse = getAccountByTokenStep.runStep(responseStep, requestDto.getToken());
        if (accountResponse.getError()) return responseStep.getStep(false, new ArrayList<>());
        val account = accountResponse.getData();
        // Get Clinic Ids For Account Step
        val accountId = account.getId();
        val clinicIdsResponse = getClinicIdsForAccountStep.runStep(responseStep, accountId);
        if (clinicIdsResponse.getError()) return responseStep.getStep(false, new ArrayList<>());
        val clinicIds = clinicIdsResponse.getData();
        // Get Clinic By Ids
        var clinicsResponse = getClinicByIdsStep.runStep(responseStep, clinicIds);
        if (clinicsResponse.getError()) return responseStep.getStep(false, new ArrayList<>());
        val clinics = clinicsResponse.getData();
        // Return response
        responseStep.addMessage("The clinic has been read successfully!");
        return responseStep.getStep(true, clinics);
    }

    @PostMapping("clinic-update")
    public ResponseEntity<ResponseDataDto<Clinic>> update(@Valid @RequestBody ClinicUpdateRequestDto requestDto) throws Exception {
        // Init
        val responseStep = this.responseStep.getObject();
        responseStep.getRidOfMessages();
        val clinicId = requestDto.getId();
        // Get Account By Token Step
        val accountResponse = getAccountByTokenStep.runStep(responseStep, requestDto.getToken());
        if (accountResponse.getError()) return responseStep.getStep(false, com.jakubolejarczyk.vet_server.model.dependent.Clinic.builder().build());
        val account = accountResponse.getData();
        // Get Clinic Ids For Owner Account Step
        val accountId = account.getId();
        val clinicIdsResponse = getClinicIdsForOwnerAccountStep.runStep(responseStep, accountId);
        if (clinicIdsResponse.getError()) return responseStep.getStep(false, com.jakubolejarczyk.vet_server.model.dependent.Clinic.builder().build());
        val clinicIds = clinicIdsResponse.getData();
        // Check Permission Step
        val permissionResponse = checkPermissionStep.runStep(responseStep, clinicIds, clinicId);
        if (permissionResponse.getError()) return responseStep.getStep(false, com.jakubolejarczyk.vet_server.model.dependent.Clinic.builder().build());
        // Get Current Clinic By Id
        val currentClinicResponse = getClinicByIdStep.runStep(responseStep, clinicId);
        if (currentClinicResponse.getError()) return responseStep.getStep(false, com.jakubolejarczyk.vet_server.model.dependent.Clinic.builder().build());
        val currentClinic = currentClinicResponse.getData();
        // Update Clinic Step
        val updateResponse = updateClinicStep.runStep(responseStep, requestDto, currentClinic);
        if (updateResponse.getError()) return responseStep.getStep(false, com.jakubolejarczyk.vet_server.model.dependent.Clinic.builder().build());
        val clinic = updateResponse.getData();
        // Return response
        responseStep.addMessage("The clinic have been updated successfully!");
        return responseStep.getStep(true, clinic);
    }

    @PostMapping("clinic-delete")
    public ResponseEntity<ResponseDto> delete(@Valid @RequestBody DeleteRequestDto requestDto) {
        // Init
        val responseStep = this.responseStep.getObject();
        responseStep.getRidOfMessages();
        val clinicIds = requestDto.getIds();
        // Get Account By Token Step
        val accountResponse = getAccountByTokenStep.runStep(responseStep, requestDto.getToken());
        if (accountResponse.getError()) return responseStep.getStep(false);
        val account = accountResponse.getData();
        // Get Employment By AccountId And ClinicIdsIn And IsOwner Step
        var accountId = account.getId();
        val employmentResponse = getEmploymentByAccountIdAndClinicIdsInAndIsOwnerStep.runStep(responseStep, accountId, clinicIds);
        if (employmentResponse.getError()) return responseStep.getStep(false);
        val employment = employmentResponse.getData();
        // Update Employment Ids Is Archived Step
        val employmentIds = employment.stream()
            .map(Employment::getId)
            .collect(Collectors.toCollection(ArrayList::new));
        updateEmploymentIdsIsArchivedStep.runStep(responseStep, employmentIds, true);
        // Update Clinic Ids Is Archived Step
        val gotClinicIds = employment.stream()
                .map(Employment::getClinicId)
                .collect(Collectors.toCollection(ArrayList::new));
        updateClinicIdsIsArchivedStep.runStep(responseStep, gotClinicIds, true);
        // Get Opening Hours Ids For Clinic Ids Step
        val openingHoursIdsResponse = getOpeningHoursIdsForClinicIdsStep.runStep(responseStep, clinicIds);
        if (openingHoursIdsResponse.getError()) return responseStep.getStep(false);
        val openingHoursIds = openingHoursIdsResponse.getData();
        // Update Opening Hours Ids Is Archived Step
        val openingHoursResponse = updateOpeningHoursIdsIsArchivedStep.runStep(responseStep, openingHoursIds, true);
        if (openingHoursResponse.getError()) return responseStep.getStep(false);
        // Return response
        responseStep.addMessage("The clinic have been deleted successfully!");
        return responseStep.getStep(true);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.getObject().handle(ex);
    }
}
