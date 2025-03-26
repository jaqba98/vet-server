package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.ChooseRoleRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.GetAccountByToken;
import com.jakubolejarczyk.vet_server.service.step.ResponseStep;
import com.jakubolejarczyk.vet_server.service.step.SetAccountRoleStep;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ChooseRoleController {
    private final GetAccountByToken getAccountByTokenStep;
    private final SetAccountRoleStep setAccountRoleStep;
    private final ObjectFactory<ResponseStep> responseStep;
    private final ObjectFactory<HandleValidationService> handleValidationService;

    @PostMapping("choose-role")
    public ResponseEntity<ResponseDto> chooseRole(@Valid @RequestBody ChooseRoleRequestDto requestDto) {
        // Init
        val responseStep = this.responseStep.getObject();
        responseStep.getRidOfMessages();
        val role = requestDto.getRole();
        // Get Account By Token Step
        val accountResponse = getAccountByTokenStep.runStep(responseStep, requestDto.getToken());
        if (accountResponse.getError()) return responseStep.getStep(false);
        val account = accountResponse.getData();
        // Set Account Role Step
        setAccountRoleStep.runStep(responseStep, account, role);
        // Return response
        return responseStep.getStep(true);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.getObject().handle(ex);
    }
}
