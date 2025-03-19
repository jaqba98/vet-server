package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.ChooseRoleRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.service.step.SuccessResponseStep;
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
    private final GetAccountByTokenStep<String> getAccountByTokenStep;
    private final UpdateAccountRoleStep updateAccountRoleStep;
    private final HandleValidationService handleValidationService;
    private final SuccessResponseStep<String> successResponseStep;

    @PostMapping("choose-role")
    public ResponseEntity<ResponseDto<String>> chooseRole(@Valid @RequestBody ChooseRoleRequestDto requestDto) {
        val account = getAccountByTokenStep.getAccount(requestDto.getToken());
        if (account.isEmpty()) {
            return getAccountByTokenStep.buildErrorResponse("");
        }
        this.updateAccountRoleStep.updateRole(account.get(), requestDto.getRole());
        return successResponseStep.getSuccessResponse("The role has been chosen!", "");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<String>> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.handle(ex);
    }
}
