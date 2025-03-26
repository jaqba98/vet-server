package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.controller.EmploymentRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.crud.DeleteRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
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
public class EmploymentController {
    private final ObjectFactory<ResponseStep> responseStep;
    private final GetAccountByToken getAccountByTokenStep;
    private final GetClinicIdsForAccountStep getClinicIdsForAccountStep;
    private final GetEmploymentIdsForOwnerAccountStep getEmploymentIdsForOwnerAccountStep;
    private final GetEmploymentByIdsStep getEmploymentByIdsStep;
    private final ObjectFactory<HandleValidationService> handleValidationService;

    @PostMapping("employment-create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody EmploymentRequestDto requestDto) {
        // Variables
        val objectResponseStep = responseStep.getObject();
        // Clean response
        objectResponseStep.getRidOfMessages();
        // Response
        objectResponseStep.addMessage("The employment has been created successfully!");
        return objectResponseStep.getStep(true);
    }

    @PostMapping("employment-read")
    public ResponseEntity<ResponseDataDto<ArrayList<Employment>>> read(@Valid @RequestBody TokenRequestDto requestDto) {
        // Init
        val responseStep = this.responseStep.getObject();
        responseStep.getRidOfMessages();
        // Get Account By Token Step
        val accountResponse = getAccountByTokenStep.runStep(responseStep, requestDto.getToken());
        if (accountResponse.getError()) return responseStep.getStep(false, new ArrayList<>());
        val account = accountResponse.getData();
        // Get Employment Ids For Owner Account Step
        val accountId = account.getId();
        val employmentIdsResponse = getEmploymentIdsForOwnerAccountStep.runStep(responseStep, accountId);
        if (employmentIdsResponse.getError()) return responseStep.getStep(false, new ArrayList<>());
        val employmentIds = employmentIdsResponse.getData();
        // Get Employment By Ids Step
        val employmentResponse = getEmploymentByIdsStep.runStep(responseStep, employmentIds);
        if (employmentResponse.getError()) return responseStep.getStep(false, new ArrayList<>());
        val employment = employmentResponse.getData();
        // Return response
        responseStep.addMessage("The employment has been read successfully!");
        return responseStep.getStep(true, employment);
    }

    @PostMapping("employment-update")
    public ResponseEntity<ResponseDataDto<Employment>> update(@Valid @RequestBody EmploymentRequestDto requestDto) {
        // Variables
        val objectResponseStep = responseStep.getObject();
        // Clean response
        objectResponseStep.getRidOfMessages();
        // Response
        objectResponseStep.addMessage("The employment has been updated successfully!");
        return objectResponseStep.getStep(true, Employment.builder().build());
    }

    @PostMapping("employment-delete")
    public ResponseEntity<ResponseDto> delete(@Valid @RequestBody DeleteRequestDto requestDto) {
        // Variables
        val objectResponseStep = responseStep.getObject();
        // Clean response
        objectResponseStep.getRidOfMessages();
        // Response
        objectResponseStep.addMessage("The employment has been deleted successfully!");
        return objectResponseStep.getStep(true);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.getObject().handle(ex);
    }
}
