package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.ChooseRoleRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
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
    private final GetAccountByTokenStep<String> getAccountByTokenStep;
    private final UpdateAccountRoleStep updateAccountRoleStep;
    private final HandleValidationService handleValidationService;
    private final ResponseStep<String> responseStep;

    @PostMapping("choose-role")
    public ResponseEntity<ResponseDataDto<String>> chooseRole(@Valid @RequestBody ChooseRoleRequestDto requestDto) {
        val token = requestDto.getToken();
        val role = requestDto.getRole();
        val account = getAccountByTokenStep.runStep(token);
        if (account.getSuccess()) {
            this.updateAccountRoleStep.updateRole(account.getData(), role);
            responseStep.addMessage("The role has been chosen!");
            return responseStep.getStep(true, role);
        }
        responseStep.addMessage("Failed to set role!");
        return responseStep.getStep(false, role);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.handle(ex);
    }
}
