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
        // Clean response
        responseStep.getObject().getRidOfMessages();
        // Get account by token
        val token = requestDto.getToken();
        val account = getAccountByTokenStep.runStep(responseStep.getObject(), token);
        if (account.getError()) {
            responseStep.getObject().addMessage("Failed to set role!");
            return responseStep.getObject().getStep(false);
        }
        val accountData = account.getData();
        // Set account role
        val role = requestDto.getRole();
        setAccountRoleStep.runStep(accountData, role);
        // Response
        responseStep.getObject().addMessage("Role set correctly!");
        return responseStep.getObject().getStep(true);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.getObject().handle(ex);
    }
}
