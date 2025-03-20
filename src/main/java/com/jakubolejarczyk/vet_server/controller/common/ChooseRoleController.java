package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.ChooseRoleRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.service.step.ResponseStep;
import com.jakubolejarczyk.vet_server.service.step.UpdateAccountRoleStep;
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
    private final HandleValidationService handleValidationService;
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final UpdateAccountRoleStep updateAccountRoleStep;
    private final ResponseStep responseStep;

    @PostMapping("choose-role")
    public ResponseEntity<ResponseDto> chooseRole(@Valid @RequestBody ChooseRoleRequestDto requestDto) {
        val account = getAccountByTokenStep.runStep(requestDto.getToken());
        if (account.isEmpty()) {
            responseStep.addMessage("Failed to set role!");
            return responseStep.getStep(false);
        }
        val accountByToken = account.get();
        val role = requestDto.getRole();
        updateAccountRoleStep.runStep(accountByToken, role);
        responseStep.addMessage("Role set correctly!");
        return responseStep.getStep(true);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.handle(ex);
    }
}
