package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.controller.OpeningHoursRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class OpeningHoursController {
    private final ObjectFactory<ResponseStep> responseStep;
    private final GetAccountByToken getAccountByTokenStep;
    private final GetClinicIdsForAccountStep getClinicIdsForAccountStep;
    private final GetOpeningHoursIdsForClinicIdsStep getOpeningHoursIdsForClinicIdsStep;
    private final GetOpeningHoursByIdsStep getOpeningHoursByIdsStep;
    private final GetClinicIdsForOwnerAccountStep getClinicIdsForOwnerAccountStep;
    private final CheckPermissionStep checkPermissionStep;
    private final UpdateOpeningHoursStep updateOpeningHoursStep;
    private final ObjectFactory<HandleValidationService> handleValidationService;

    @PostMapping("opening-hours-read")
    public ResponseEntity<ResponseDataDto<ArrayList<OpeningHours>>> read(@Valid @RequestBody TokenRequestDto requestDto) {
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
        // Get Opening Hours Ids For Clinic Ids Step
        val openingHoursIdsResponse = getOpeningHoursIdsForClinicIdsStep.runStep(responseStep, clinicIds);
        if (openingHoursIdsResponse.getError()) return responseStep.getStep(false, new ArrayList<>());
        val openingHoursIds = openingHoursIdsResponse.getData();
        // Get Opening Hours By Ids Step
        val openingHoursResponse = getOpeningHoursByIdsStep.runStep(responseStep, openingHoursIds);
        if (openingHoursResponse.getError()) return responseStep.getStep(false, new ArrayList<>());
        val openingHours = openingHoursResponse.getData();
        // Return response
        responseStep.addMessage("The opening hours have been read successfully!");
        return responseStep.getStep(true, openingHours);
    }

    @PostMapping("opening-hours-update")
    public ResponseEntity<ResponseDataDto<OpeningHours>> update(@Valid @RequestBody OpeningHoursRequestDto requestDto) {
        // Init
        val responseStep = this.responseStep.getObject();
        responseStep.getRidOfMessages();
        val openingHoursId = requestDto.getId();
        // Get Account By Token Step
        val accountResponse = getAccountByTokenStep.runStep(responseStep, requestDto.getToken());
        if (accountResponse.getError()) return responseStep.getStep(false, OpeningHours.builder().build());
        val account = accountResponse.getData();
        // Get Clinic Ids For Owner Account Step
        val accountId = account.getId();
        val clinicIdsResponse = getClinicIdsForOwnerAccountStep.runStep(responseStep, accountId);
        if (clinicIdsResponse.getError()) return responseStep.getStep(false, OpeningHours.builder().build());
        val clinicIds = clinicIdsResponse.getData();
        // Get Opening Hours Ids For Clinic Ids Step
        val openingHoursIdsResponse = getOpeningHoursIdsForClinicIdsStep.runStep(responseStep, clinicIds);
        if (openingHoursIdsResponse.getError()) return responseStep.getStep(false, OpeningHours.builder().build());
        val openingHoursIds = openingHoursIdsResponse.getData();
        // Check Permission Step
        val permissionResponse = checkPermissionStep.runStep(responseStep, openingHoursIds, openingHoursId);
        if (permissionResponse.getError()) return responseStep.getStep(false, OpeningHours.builder().build());
        // Update the opening hours
        val updateResponse = updateOpeningHoursStep.runStep(responseStep, requestDto);
        if (updateResponse.getError()) return responseStep.getStep(false, OpeningHours.builder().build());
        val openingHours = updateResponse.getData();
        // Return response
        responseStep.addMessage("The opening hours have been updated successfully!");
        return responseStep.getStep(true, openingHours);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.getObject().handle(ex);
    }
}
