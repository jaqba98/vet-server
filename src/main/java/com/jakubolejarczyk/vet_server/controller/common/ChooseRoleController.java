package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.ChooseRoleRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.service.step.ResponseStep;
import com.jakubolejarczyk.vet_server.service.step.SetAccountRoleStep;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ChooseRoleController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final SetAccountRoleStep setAccountRoleStep;
    private final ResponseStep responseStep;
    private final HandleValidationService handleValidationService;

    @PostMapping("choose-role")
    public ResponseEntity<ResponseDto> chooseRole(@Valid @RequestBody ChooseRoleRequestDto requestDto) {
        // Get account by token
        val token = requestDto.getToken();
        val account = getAccountByTokenStep.runStep(token);
        if (!account.getSuccess()) {
            responseStep.addMessage("Failed to set role!");
            return responseStep.getStep(false);
        }
        val accountData = account.getData();
        // Set account role
        val role = requestDto.getRole();
        setAccountRoleStep.runStep(accountData, role);
        // Response
        responseStep.addMessage("Role set correctly!");
        return responseStep.getStep(true);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.handle(ex);
    }
}
